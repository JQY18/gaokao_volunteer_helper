from django.urls import path
from . import views3
from . import views

urlpatterns = [
    path('api/info/', views.get_data),
    path('info/', views3.info, name='info'),
    path('analy', views3.analy, name='analy')
]
