# Generated by Django 4.2 on 2025-04-22 16:19

from django.db import migrations


class Migration(migrations.Migration):

    dependencies = [
        ('studentinfo', '0003_enrollmentdetails'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='enrollmentdetails',
            name='enrolledcourses',
        ),
    ]
