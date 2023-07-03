# To create spring boot project
I used spring initializer site - https://start.spring.io/ to create spring boot project. Since, we have to create
rest endpoints, I added spring web dependency and used following configurations to create project.
- **groupId** : com.assignment
- **artifactId** : demo-assignment-service
- **spring-boot-version** : 3.1.1
- **Java** : 17
- **build type** : maven

After creating project, I imported project structure to my Intellij IDEA and created following packages.
- controller
- service & impl
- model
- util & impl

Also, I created a health check endpoint to just make sure I have some api to check health of my service.
health check api - http://localhost:18080/health/version

# Fetch Json data from : https://freegeoip.app/json/
I tried to solve this problem in 2 ways using Rest Controllers as I need to create REST endpoints.
- First, I tried to read data using URL & Json Reader.
    - This is the API endpoint : http://localhost:18080/assignment/getGeoObject
- Secondly,
    - I used Rest Template approach. I created one configuration class to create rest template as bean so that I don't
      need to create it multiple time.
    - I also created custom response body to hold response data and any error message.
    - This is the API endpoint : http://localhost:18080/api/V1/data/location

# Export and save location data
I tried to solve this problem in 2 ways using Rest Controllers as I need to create REST endpoints.
- First, I tried to read data as in previous problem and using CSVPrinter and PrintWriter and I converted the data into
  CSV and save the file in root location of project. API endpoint is : http://localhost:18080/assignment/csv
- Secondly, I used the rest template approach to read the data, CSVPrinter and PrintWriter to create csv data and used
  spring resource to download the file. API endpoint is : http://localhost:18080/api/V1/data/csv/download. 








