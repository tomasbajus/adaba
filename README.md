# To build executable jar run: gradle clean bootJar
## jar is in /build/libs/

# To run the server execute java -jar *.jar in folder /build/libs

Available endpoints
# GET /topics
# POST /topics
# GET /topics/$topicsId
# GET /subscribers
# POST /subscribers
# GET /subscribers/$subscribedId
# GET /subscribers/pull/$subscribedId
# POST /messages
# GET /context/$subscriberId/$topicId
# DELETE /context/$subscriberId/$topicId

