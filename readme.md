Install JDK 8 [http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html]

Install Maven [https://maven.apache.org/download.cgi]

cd goBear-challenge2

mvn clean install

mvn -Dtest=InsuranceTest test

report: \target\surefire-reports\emailable-report.html
# goBear-challenge2
