# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('tegchos_solicitudes', '0004_auto_20151108_1035'),
    ]

    operations = [
        migrations.AddField(
            model_name='techosconstruidos',
            name='imagen',
            field=models.CharField(help_text=b'Imagen', max_length=70, null=True, blank=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='techosconstruidos',
            name='DonacionPalo',
            field=models.CharField(help_text=b'Donacion de Palo', max_length=70, null=True, blank=True),
            preserve_default=True,
        ),
    ]
