# Generated by Django 4.2 on 2025-04-21 02:01

from django.db import migrations, models


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Studentdetails',
            fields=[
                ('studentid', models.IntegerField(primary_key=True, serialize=False)),
                ('firstname', models.CharField(max_length=100)),
                ('lastname', models.CharField(max_length=100)),
                ('major', models.CharField(max_length=50)),
                ('year', models.CharField(max_length=50)),
                ('gpa', models.DecimalField(decimal_places=2, max_digits=3)),
            ],
        ),
    ]
