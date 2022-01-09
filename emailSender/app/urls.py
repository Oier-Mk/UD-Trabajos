from django.contrib import admin
from django.http.response import HttpResponse
from django.urls import path, include
from .views import *

urlpatterns = [
    path('', Form.as_view()),
    path('confirmation/', confirmation),
]
