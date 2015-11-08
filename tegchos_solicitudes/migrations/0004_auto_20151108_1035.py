# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('tegchos_solicitudes', '0003_auto_20151107_2034'),
    ]

    operations = [
        migrations.AlterField(
            model_name='techosconstruidos',
            name='DonacionPalo',
            field=models.CharField(help_text=b'Imagen', max_length=70, null=True, blank=True),
            preserve_default=True,
        ),
    ]
