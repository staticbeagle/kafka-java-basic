# kafka-java-basic
Basic Consumer-Producer application using Kafka

```shell
docker compose up -d
docker exec -it -w /opt/kafka/bin broker sh
```
## Kafka common commands
### Topic Management
- List Topics
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --list
```
- Create a topic
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic my-topic --partitions 3 --replication-factor 2
```
- Delete a topic
```shell
kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic my-topic
```

### Producers
- Send Message
```shell
kafka-console-producer.sh --bootstrap-server localhost:9092 --topic my-topic
# Type a message then press enter
```
- Send message with a key
```shell
TBD
```
### Consumers
1. Consume messages from a Topic
```shell
kafka-console-consume.sh --bootstrap-server localhost:9092 --topic my-topic --from-beginning
```
