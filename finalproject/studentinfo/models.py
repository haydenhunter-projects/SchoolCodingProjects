from django.db import models

# Create your models here.

class Studentdetails(models.Model):
    studentid = models.IntegerField(primary_key=True)
    firstname = models.CharField(max_length=100)
    lastname = models.CharField(max_length=100)
    major = models.CharField(max_length=50)
    year = models.CharField(max_length=50)
    gpa = models.DecimalField(decimal_places=2, max_digits=3)

class Coursedetails(models.Model):
    courseid = models.IntegerField(primary_key=True)
    coursetitle = models.CharField(max_length=100)
    coursename = models.CharField(max_length=100)
    studentsenrolled = models.IntegerField()
    coursedepartment = models.CharField(max_length=100)
    instructorname = models.CharField(max_length=100)

class Enrollmentdetails(models.Model):
    studentname = models.CharField(max_length=100)
    coursetype = models.CharField(max_length=100)