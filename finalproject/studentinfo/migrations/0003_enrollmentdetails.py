# Generated by Django 4.2 on 2025-04-22 15:31

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('studentinfo', '0002_coursedetails'),
    ]

    operations = [
        migrations.CreateModel(
            name='Enrollmentdetails',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('studentname', models.CharField(max_length=100)),
                ('coursetype', models.CharField(max_length=100)),
                ('enrolledcourses', models.IntegerField()),
            ],
        ),
    ]
