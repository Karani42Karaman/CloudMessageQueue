# RabbitMQ MQTT Producer & Consumer - Kubernetes (EKS)

## 📌 Proje Açıklaması

Bu proje, **RabbitMQ** ve **MQTT protokolü** kullanarak çalışan bir **Producer-Consumer** mimarisi içermektedir. Producer, mesajları MQTT protokolü aracılığıyla RabbitMQ'ya gönderir ve Consumer, bu mesajları alarak işler. **AWS EKS (Elastic Kubernetes Service)** üzerinde çalışan **Dockerize edilmiş** microservice uygulamaları kullanılarak sistem konteyner tabanlı bir yapıya taşınmıştır.

Bir **web arayüzü** üzerinden Consumer tarafından alınan mesajlar görüntülenebilir.

## 🛠 Kullanılan Teknolojiler

- **Java**: Microservice geliştirme
- **Spring Boot**: Producer ve Consumer servisleri
- **RabbitMQ**: Mesaj kuyruğu yönetimi
- **MQTT**: Hafif ve hızlı mesajlaşma protokolü
- **Docker**: Servisleri konteynerleştirmek için
- **Kubernetes (AWS EKS)**: Mikroservislerin yönetimi ve ölçeklenmesi
- **HTML & JavaScript**: Web arayüzü

## 🔧 Proje Yapısı

```
├── rabbitmq-producer  # MQTT mesajlarını RabbitMQ'ya gönderen servis
├── rabbitmq-mqtt-consumer  # RabbitMQ'dan mesajları alan servis
├── rabbitmq          # RabbitMQ ve MQTT eklentileri ile yapılandırılmış mesaj kuyruğu
├── eks               # Kubernetes deployment ve service YAML dosyaları
├── docker            # Docker dosyaları ve yapılandırmaları
└── README.md         # Proje açıklamaları
```

## 🚀 Çalıştırma Adımları

### 1️⃣ **Dockerize Etme**

Öncelikle, Producer ve Consumer servislerini **Docker konteynerlerine** alıyoruz.

```sh
docker build -t rabbitmq-mqtt-producer ./producer-service
docker build -t rabbitmq-mqtt-consumer ./consumer-mqtt-service
```

Ardından, **Docker Hub'a** veya kendi registry'ine push edebilirsin:

```sh
docker push ayyildiz42/rabbitmq-mqtt-producer
docker push ayyildiz42/rabbitmq-mqtt-consumer
```

### 2️⃣ **Kubernetes Üzerinde Çalıştırma**

AWS EKS veya yerel bir Kubernetes ortamında pod'ları çalıştırmak için aşağıdaki adımları takip edebilirsin.

#### 📌 **RabbitMQ'yu Dağıtma**

```sh
kubectl apply -f eks/rabbitmq-deployment.yaml
```

#### 📌 **Producer ve Consumer Dağıtımı**

```sh
kubectl apply -f eks/rabbitmq-mqtt-producer-deployment.yaml
kubectl apply -f eks/rabbitmq-mqtt-consumer-deployment.yaml
```

 
### 3️⃣ **Logları Kontrol Etme**

Uygulamanın sorunsuz çalıştığını doğrulamak için **logları inceleyebilirsin**:

```sh
kubectl logs -l app=rabbitmq-mqtt-producer
kubectl logs -l app=rabbitmq-mqtt-consumer
kubectl logs -l app=rabbitmq
```

## 🌐 API Uç Noktaları

### 📤 **Producer API (Mesaj Gönderme)**

Producer servisi aşağıdaki API uç noktasını kullanarak mesajları RabbitMQ'ya gönderir:

```
GET http://a086e6fe389da4a6b839c94e3b7ab23a-1797338516.eu-north-1.elb.amazonaws.com:8080/api/producer/send?message=<mesaj>
```

**Örnek:**

```
GET http://a086e6fe389da4a6b839c94e3b7ab23a-1797338516.eu-north-1.elb.amazonaws.com:8080/api/producer/send?message=görev başarılı karani
```

### 📥 **Consumer API (Mesajları Görüntüleme)**

Consumer servisi alınan mesajları bir web arayüzüne ileterek aşağıdaki adresten görüntülenmesini sağlar:

```
http://a7e8010737b3648e4b8c2ce33901087d-1734708145.eu-north-1.elb.amazonaws.com:8081/index.htmlé
```

Burada **MQTT ile gönderilen mesajlar** listelenmektedir.

## 📈 Projenin Amacı

- **IoT veya real-time veri işleme** için **hafif, hızlı ve ölçeklenebilir** bir mesajlaşma mimarisi kurmak.
- AWS EKS üzerinde **container tabanlı bir çözüm** oluşturarak **yüksek erişilebilirlik ve yönetilebilirlik sağlamak**.
- **RabbitMQ ile MQTT protokolünü** entegre ederek **performanslı bir message queue çözümü** geliştirmek.
- **Web tabanlı bir arayüz ile MQTT mesajlarını canlı takip edebilmek.**



