# Measurements

A demo on winter school 2023@UM/FERI:
- SpringBoot backend
- React frontent
- Complete CI/CD:
  - Build
  - Unit test automation
  - Quality Gate automation
  - Functional test automation
  - Containerized Deployment to a Cloud environment

## Scope (Backend + Frontend + CLI)
- App stores **Products** (id / name / min allowed temperature / max allowed termperature) in DB;
  **CRUD** operations are available on Products via REST endpoint,
- It enables sending **Measurements** (product / temperature / measurement type) via REST endpoint,
- When recorded, **measurements are marked** as OK (inside allowed temperature range) or NOT OK,
- **Measurements history** for last 10 days is available via REST endpoint.

## Running Backend
- build & run *or*
- docker-compose build & docker-compose up

Then check: http://127.0.0.1:8280/api/v1/swagger-ui.html


Unzip'n'run platform: https://github.com/lukapavlic/platforms/tree/master/winter_school_2023


For details check _measurements_backend_ folder.

## Running Frontend
- npm install & npm run *or*
- `docker run -d -p 3000:80 <image_name>`

For details check _measurements_frontend/measurements_ folder.


## Running CLI
- java si.um.feri.measurements.MeasCLI --help

For details check _measurements_cli_ folder.
