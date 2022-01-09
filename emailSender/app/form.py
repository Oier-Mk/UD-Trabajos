from django import forms
from django.core.mail import send_mail
from emailSender.settings import EMAIL_HOST_USER


class Email(forms.Form):
    email = forms.EmailField()
    subject = forms.CharField(max_length=50)
    message = forms.CharField(widget=forms.Textarea)

    def send_email(self):
        # send email using the self.cleaned_data dictionary
        print("$$$$$$$$$$$$$$$$$$$ -- sending email -- $$$$$$$$$$$$$$$$$$$")
        print("inicio")
        send_mail(
            self.cleaned_data['subject'],
            self.cleaned_data['message'],
            EMAIL_HOST_USER,
            [self.cleaned_data['email']],
            fail_silently=False,
        )
        print("$$$$$$$$$$$$$$$$$$$ -- email sent -- $$$$$$$$$$$$$$$$$$$")
