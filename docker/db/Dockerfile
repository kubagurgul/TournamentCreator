FROM microsoft/mssql-server-linux

EXPOSE 1433

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY . /usr/src/app

CMD /bin/bash ./entrypoint.sh




