# This dockerfile is used only to start the database.
# The app should be run as normal
# Build with
    # docker build -t mysql-bookstore .
# Run with
    # docker run -d -p 3306:3306 --name mysql-bookstore mysql-bookstore

FROM mysql:8
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=bookstore
EXPOSE 3306
