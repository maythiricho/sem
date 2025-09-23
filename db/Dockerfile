FROM mysql:latest

# Set root password
ENV MYSQL_ROOT_PASSWORD=example

# Copy all schema + dump files to init directory
COPY test_db/*.sql /docker-entrypoint-initdb.d/
COPY test_db/*.dump /docker-entrypoint-initdb.d/
