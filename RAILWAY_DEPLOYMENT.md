# Temporary Railway demo deployment

This guide prepares TruthLens for a public demo backed by persistent PostgreSQL. The local default remains H2 for development; Railway must use PostgreSQL and a private JWT secret.

## Railway free-tier note

Railway currently advertises a one-time $5 trial credit for 30 days and $1/month in usage credits on its Free plan. That may be enough for a short evaluation demo, but it does **not** guarantee continuous free hosting: app and database usage consume credits and the service can stop when credits run out. Check the current pricing and usage in your Railway account before deploying. Do not add a payment method or upgrade unless you decide to.

## Deploy

1. Push this project to a GitHub repository you control, then sign in to Railway and create a project from that repository.
2. Add a Railway PostgreSQL service to the same project. Railway must connect the app service to this database.
3. In the app service's variables, add the following. Replace `Postgres` in the Railway variable references if Railway named your database service differently:

   ```text
   SPRING_DATASOURCE_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}
   SPRING_DATASOURCE_USERNAME=${{Postgres.PGUSER}}
   SPRING_DATASOURCE_PASSWORD=${{Postgres.PGPASSWORD}}
   SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
   SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
   H2_CONSOLE_ENABLED=false
   JWT_SECRET=<unique base64-encoded random key, at least 32 bytes>
   ```

   Generate the JWT key locally with `openssl rand -base64 32`. Set it only in Railway's private service variables; never put the actual key in source control or share it publicly. Railway supplies `PORT` to the app automatically.
4. Deploy the app service, wait for the `/` health check to pass, then use Railway's **Generate Domain** action to create its public HTTPS URL.
5. Open the generated URL and test registration, login, workshops, survey submission, and fact-checking before the evaluation.
6. After the October 10–11 evaluation, stop or delete the Railway app and database if you no longer need the public demo. Export any data you want to keep first.

The repository does not contain Railway account credentials and cannot create or publish a cloud service on your behalf. The final deployment must be authorized from your Railway account. Do not use production or personal data in the demo database.
