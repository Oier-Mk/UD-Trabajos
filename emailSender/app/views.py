from django.shortcuts import render
from django.views.generic import *
from .form import *
from django.http.response import HttpResponse

# Create your views here.

class Form(FormView):
    template_name = 'mail.html'
    form_class = Email
    success_url = '/confirmation/'

    def form_valid(self, form):
        form.send_email()
        return super().form_valid(form)


def confirmation(request):
    return HttpResponse("Email sent")