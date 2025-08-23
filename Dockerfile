FROM eclipse-temurin:17-jre

WORKDIR /app

# copia o jar gerado localmente (precisa do target no contexto!)
COPY target/*.jar app.jar

EXPOSE 8080

# tuning opcional da JVM
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75.0 -XX:InitialRAMPercentage=10.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
