from django.conf.urls import include, url
from . import views

urlpatterns = [
        url(r'^$', views.inicio),
        url(r'^tegchos/solicitar/$', views.solicitar, name='solicitar'),
]