FROM python:3.8

WORKDIR /code
RUN apt update -y && apt install -y libgl1-mesa-glx
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY ./ .
EXPOSE 5000
CMD [ "python", "./app.py" ]
