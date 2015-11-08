# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Colonia',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('Nombre', models.CharField(help_text=b'Nombre de la colonia', max_length=120)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Solicitud',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('NombreSolicitante', models.CharField(help_text=b'Nombre del solicitante', max_length=120)),
                ('Ubicacion', models.CharField(help_text=b'Ubicacion', max_length=70)),
                ('Observaciones', models.CharField(help_text=b'Observaciones', max_length=120)),
                ('Telefono', models.CharField(help_text=b'Telefono', max_length=15)),
                ('Correo', models.EmailField(help_text=b'Correo electronico', max_length=75)),
                ('Fecha', models.DateField(auto_now=True)),
                ('Colonia', models.ForeignKey(to='tegchos_solicitudes.Colonia')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='TechosConstruidos',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('FechaConstruccion', models.DateField(auto_now=True)),
                ('Beneficiarios', models.IntegerField()),
                ('MetroS2', models.DecimalField(max_digits=10, decimal_places=2)),
                ('DonacionPalo', models.CharField(help_text=b'Donacion de Palo', max_length=70)),
                ('DonacionLamina', models.CharField(help_text=b'Donacion de Lamina', max_length=70)),
                ('Solicitud', models.ForeignKey(to='tegchos_solicitudes.Solicitud')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
    ]
