from itertools import count
from django.shortcuts import render
from django.http import HttpResponse
from studentinfo import models
from studentinfo.models import Studentdetails
from studentinfo.models import Coursedetails
from studentinfo.models import Enrollmentdetails
from django.core.paginator import Paginator
from django.contrib.auth.decorators import login_required

# Importing the Avg function to calculate the average GPA
# https://docs.djangoproject.com/en/5.1/topics/db/aggregation/
from django.db.models import Avg, Count

# Create your views here.

@login_required
def dashboard(request):
    # Django query set statement to retrieve all data from the studentinfo_coursedetails table
    coursedata = Coursedetails.objects.all()

    # Django query set statement to retrieve the total enrollment number from studentinfo_studentdetails table
    total_enrollment = Enrollmentdetails.objects.count() # select count(*) from studentinfo_enrollmentdetails
    
    # Django query set statement to retrieve the total number of courses from studentinfo_coursedetails table
    total_courses = coursedata.count() # select count(*) from studentinfo_coursedetails

    # Django query set statement to retrieve the total number of students from studentinfo_studentdetails table
    total_students = Studentdetails.objects.count() # select count(*) from studentinfo_studentdetails
    
    # Django query set statement to calculate the average GPA from studentinfo_studentdetails table
    # https://docs.djangoproject.com/en/5.1/topics/db/aggregation/
    average_gpa = Studentdetails.objects.aggregate(Avg("gpa")) # select avg(gpa) from studentinfo_studentdetails

    # Django query set statement to count the number of each student type
    # Using django Count and annotate to group by year and count the number of students in each year
    # https://docs.djangoproject.com/en/5.1/topics/db/aggregation/#counting-objects
    studenttype = Studentdetails.objects.values('year').annotate(count=Count('year'))

    # Convert the QuerySet to a dictionary
    # This will create a dictionary where the key is the year and the value is the count of students in that year
    # Example: {'Freshman': 100, 'Sophomore': 120, 'Junior': 130, 'Senior': 110}
    studenttype_dict = {entry['year']: entry['count'] for entry in studenttype}

    # Django query set statement to retrieve the total number of departments from studentinfo_coursedetails table
    total_departments = Coursedetails.objects.values('coursedepartment').distinct().count() # select count(distinct coursedepartment) from studentinfo_coursedetails

    context = {
        "course": coursedata, 
        "total_enrollment": total_enrollment, # select count(*) from studentinfo_enrollmentdetails
        "total_courses": total_courses, # select count(*) from studentinfo_coursedetails
        "total_students": total_students, # select count(*) from studentinfo_studentdetails
        "average_gpa": average_gpa, # select avg(gpa) from studentinfo_studentdetails
        "studenttype": studenttype_dict, # Dictionary of student types with their counts
        "total_departments": total_departments # select count(distinct coursedepartment) from studentinfo_coursedetails
        }
    return render(request, "studentinfo/dashboard.html", context)

@login_required
def home(request):
    #return HttpResponse("This is the home page")
    return render(request, "studentinfo/base.html")

@login_required
def studentdetails(request):
    # Django query set statement to retrieve all data from the studentinfo_studentdetails table
    # Django query set is django's abstraction of SQL commands
    data = Studentdetails.objects.all() # select * from studentinfo_studentdetails

    # Creating a paginator object from the paginator class
    paginator = Paginator(data, 10)

    page = request.GET.get('page')
    page_data = paginator.get_page(page)

    # show all parameters of the request
    # print(request.__dict__)

    context = {"studentdata": page_data}
    return render(request, "studentinfo/studentdetails.html", context)

@login_required
def coursedetails(request):
    # Django query set statement to retrieve all data from the studentinfo_coursedetails table
    # Django query set is django's abstraction of SQL commands
    data = Coursedetails.objects.all() # select * from studentinfo_coursedetails

    # Creating a paginator object from the paginator class
    paginator = Paginator(data, 10)

    page = request.GET.get('page')
    page_data = paginator.get_page(page)

    # show all parameters of the request
    # print(request.__dict__)

    context = {"coursedata": page_data}
    return render(request, "studentinfo/coursedetails.html", context)

@login_required
def enrollmentinfo(request):
    studentdata = Studentdetails.objects.all()
    coursedata = Coursedetails.objects.all()

    # Creating a paginator object from the paginator class
    enrollmentdata = Enrollmentdetails.objects.all()

    paginator = Paginator(enrollmentdata, 10)

    page = request.GET.get('page')
    enrollmentpagedata = paginator.get_page(page)

    

    context = {
        'student':studentdata, 
        'course': coursedata, 
        'enrollment': enrollmentpagedata}

    return render(request, "studentinfo/enrollment.html", context)

def saveenrollment(request):
    if("studentdata" in request.GET and "coursedata" in request.GET):
        # Extract the facultydata from the request object
        student = request.GET.get("studentdata")
        course = request.GET.get("coursedata")
        # Django query set statement to get the count of rows where the student name exists
        # select count(*) from studentinfo_enrollmentdetails where student = 'studentname'
        studentcount = Enrollmentdetails.objects.filter(studentname = student).count()

        # Django query set statement to check if the student is already enrolled in the course
        alreadyenrolled = Enrollmentdetails.objects.filter(studentname = student, coursetype = course).count()

        # a student cannot be enrolled in more than 3 courses
        if(studentcount == 3):
            return HttpResponse("limit_reached")
        # a student cannot be enrolled in the same course more than once
        if(alreadyenrolled > 0):
            return HttpResponse("already_enrolled")
        # if the student is not enrolled in the course and has less than 3 courses enrolled, we can proceed with the enrollment
        if(studentcount < 3):
            data = Enrollmentdetails(studentname = student, coursetype = course)
            # insert a new row of data into the Nomination details table
            data.save()

            # When a succesfful enrollment is made, we need to update the studentsenrolled field in the Coursedetails table
            course_data = Coursedetails.objects.get(coursetitle=course)
            course_data.studentsenrolled += 1
            course_data.save()
            return HttpResponse("success")
        
    return HttpResponse("error")
