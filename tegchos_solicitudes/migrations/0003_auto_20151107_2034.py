# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('tegchos_solicitudes', '0002_auto_20151107_2030'),
    ]

    operations = [
        migrations.AlterField(
            model_name='techosconstruidos',
            name='DonacionLamina',
            field=models.CharField(help_text=b'Donacion de Lamina', max_length=70, null=True, blank=True),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='techosconstruidos',
            name='DonacionPalo',
            field=models.CharField(help_text=b'Donacion de Palo', max_length=70, null=True, blank=True),
            preserve_default=True,
        ),
    ]
