from django.conf.urls import include, url
from . import views

urlpatterns = [
        url(r'^$', views.inicio, name='inicio'),
        url(r'^tegchos/solicitar/$', views.solicitar, name='solicitar'),
        url(r'^tegchos/aceptar_solicitud/$', views.aceptar_solicitud, name='AceptarSolicitud'),
]