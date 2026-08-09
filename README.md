# 🌾 Farmer Friend

<p align="center">

<img src="https://img.shields.io/badge/Platform-Android-brightgreen?style=for-the-badge&logo=android" />
<img src="https://img.shields.io/badge/Kotlin-Android-purple?style=for-the-badge&logo=kotlin" />
<img src="https://img.shields.io/badge/AI-Gemini-blue?style=for-the-badge&logo=google" />
<img src="https://img.shields.io/badge/ML-TensorFlow%20Lite-orange?style=for-the-badge&logo=tensorflow" />
<img src="https://img.shields.io/badge/Firebase-Authentication-yellow?style=for-the-badge&logo=firebase" />
<img src="https://img.shields.io/badge/Backend-Flask-black?style=for-the-badge&logo=flask" />

</p>

<p align="center">
  <h3 align="center">🌱 AI-Powered Agricultural Assistant</h3>
</p>

<p align="center">
  <b>Empowering farmers with Artificial Intelligence, Machine Learning and modern mobile technology.</b>
</p>

<p align="center">
  Farmer Friend is an Android application that combines crop disease detection,
  AI-powered analysis, weather information, treatment recommendations,
  market information, labour assistance and user management into one platform.
</p>

---

# 📱 Project Overview

**Farmer Friend** is an Android application designed to provide practical,
technology-driven assistance to farmers.

The application combines:

- 🤖 Machine Learning
- 🧠 Generative AI
- 🌱 Crop Disease Detection
- 🌦️ Weather Information
- 💊 Treatment & Remedies
- 📈 Market Information
- 👷 Labour Assistance
- 🔐 Firebase Authentication

The goal of the project is to make agricultural technology more accessible
through a simple and farmer-friendly mobile application.

---

# 🎥 Project Demo

<p align="center">

<a href="https://www.youtube.com/watch?v=YOUR_YOUTUBE_VIDEO_ID">

<img src="https://img.youtube.com/vi/YOUR_YOUTUBE_VIDEO_ID/maxresdefault.jpg"
     width="800"
     alt="Farmer Friend Demo"/>

</a>

</p>

<p align="center">
  ▶️ <b>Click the image above to watch the Farmer Friend demo</b>
</p>

### 🎬 Demo Flow

```text
Login / Registration
        ↓
Home Dashboard
        ↓
Weather Information
        ↓
Crop Detection
        ↓
AI Disease Analysis
        ↓
Detailed Results
        ↓
Recommended Remedies
        ↓
Market Module
        ↓
Labour Module
        ↓
User Profile
```

> 📌 Upload the demo video to YouTube first, then replace
> `YOUR_YOUTUBE_VIDEO_ID` with your actual YouTube video ID.

---

# ✨ Key Features

## 🔐 1. User Authentication

Farmer Friend uses **Firebase Authentication** for user management.

### Features

- User registration
- User login
- Secure session handling
- Logout functionality
- User-specific profile information

---

## 🏠 2. Home Dashboard

The home dashboard provides quick access to the major services of the
Farmer Friend application.

### Available Modules

| Module | Purpose |
|---|---|
| 🌦️ Weather | Weather-related information |
| 🌱 Crop Detection | AI-based crop disease detection |
| 💊 Remedies | Treatment and remedy information |
| 📈 Market | Market information |
| 👷 Labour | Agricultural labour assistance |
| 👤 Profile | User account management |

---

# 🌦️ 3. Weather Information

The weather module provides weather-related information that can help
farmers make better day-to-day decisions.

### Possible Use Cases

- Checking current weather conditions
- Planning irrigation
- Planning agricultural activities
- Understanding upcoming weather conditions

---

# 🌱 4. AI-Based Crop Disease Detection

One of the core features of Farmer Friend is **AI-powered crop disease
detection**.

The application follows the pipeline:

```text
Crop Image
     ↓
Android Application
     ↓
Image Processing
     ↓
TensorFlow Lite Model
     ↓
AI / Backend Analysis
     ↓
Disease Identification
     ↓
Disease Information
     ↓
Recommended Remedies
```

The project uses **TensorFlow Lite (TFLite)** for on-device
machine-learning inference and also integrates a backend AI pipeline
for more detailed analysis.

---

# 🤖 5. AI-Powered Detailed Crop Analysis

For detailed crop analysis, the Android application communicates with
a backend service.

### Backend Technologies

- Python
- Flask
- AI / LLM integration
- Gemini API

### API Endpoints

```http
POST /analyzeCrop
POST /analyzeWithDetails
```

### Analysis Can Include

- 🌱 Detected crop/disease
- 📖 Disease explanation
- 🔍 Possible causes
- 🛠️ Recommended actions
- 💊 Treatment suggestions
- 🛡️ Preventive information

---

# 💊 6. Remedy System

Farmer Friend provides different types of treatment and remedy information.

### Remedy Categories

| Category | Description |
|---|---|
| 🧪 Chemical | Chemical-based treatment information |
| 🌿 Ayurvedic | Ayurvedic/natural approaches |
| 🌱 Organic | Organic treatment approaches |
| 🦠 Biological | Biological treatment approaches |

This allows farmers to explore multiple approaches rather than relying
on a single treatment category.

> ⚠️ **Disclaimer:** Treatment information provided by the application
> is intended for informational guidance. Farmers should verify chemical
> usage, dosage, crop suitability and local agricultural recommendations
> with qualified agricultural professionals and product labels.

---

# 📈 7. Market Module

The Market module is designed to provide farmers with market-related
information.

### Navigation Flow

```text
State
  ↓
City
  ↓
Market
  ↓
Market Information
```

The module can be extended with live agricultural market-price APIs
and additional commodity information.

---

# 👷 8. Labour Hiring Module

The Labour module is designed to help farmers with agricultural labour
requirements.

### Basic Workflow

```text
Farmer
   ↓
Labour Requirement
   ↓
Labour Information
   ↓
Hiring / Contact
```

### Future Possibilities

- Location-based labour matching
- Labour availability
- Ratings & reviews
- Booking system
- Farmer/labour profiles

---

# 👤 9. User Profile

The profile section provides a central place for user information
and account management.

### Features

- User information
- Firebase account information
- Profile management
- Logout

---

# 🏗️ System Architecture

```text
                         ┌─────────────────────┐
                         │     Android App     │
                         │    Kotlin / Java    │
                         └──────────┬──────────┘
                                    │
              ┌─────────────────────┼─────────────────────┐
              │                     │                     │
              ▼                     ▼                     ▼
      ┌──────────────┐      ┌──────────────┐      ┌──────────────┐
      │Firebase Auth │      │ TFLite Model │      │   REST APIs  │
      └──────────────┘      └──────────────┘      └──────┬───────┘
                                                         │
                                                         ▼
                                                  ┌──────────────┐
                                                  │Flask Backend │
                                                  │    Python    │
                                                  └──────┬───────┘
                                                         │
                                                         ▼
                                                  ┌──────────────┐
                                                  │  Gemini AI   │
                                                  │    / LLM     │
                                                  └──────┬───────┘
                                                         │
                                                         ▼
                                             ┌─────────────────────┐
                                             │ Agricultural         │
                                             │ Information & AI    │
                                             │ Analysis            │
                                             └─────────────────────┘
```

---

# 🔄 Crop Detection Workflow

```text
1. User opens Crop Detection
            ↓
2. User selects or captures crop image
            ↓
3. Image is processed
            ↓
4. TFLite model performs local analysis
            ↓
5. Backend performs detailed AI analysis
            ↓
6. Result is returned to Android application
            ↓
7. Disease information is displayed
            ↓
8. User can view suitable remedy information
```

---

# 🔌 API Communication

The Android application communicates with the backend using
**Retrofit** and REST APIs.

### Example API Operations

```http
POST /analyzeCrop
POST /analyzeWithDetails
```

### Communication Flow

```text
Android App
     ↓
   Retrofit
     ↓
REST API
     ↓
Flask Backend
     ↓
AI / Gemini
     ↓
Analysis Result
     ↓
Android App
```

---

# 🛠️ Technology Stack

### 📱 Android

- Kotlin
- Java
- Android Studio
- Android XML Layouts
- Activities
- Adapters
- Retrofit

### 🤖 AI / Machine Learning

- TensorFlow Lite
- Gemini API
- Generative AI
- Image-based crop analysis

### 🖥️ Backend

- Python
- Flask
- REST APIs

### ☁️ Authentication & Cloud

- Firebase Authentication
- Firebase Services

### 🌐 Networking

- Retrofit
- REST API communication
- JSON-based responses

---

# 📂 Project Structure

```text
Farmer-Friend/
│
├── app/
│   └── src/
│       └── main/
│           │
│           ├── java/
│           │   └── com/
│           │       └── example/
│           │           └── farmingfriend/
│           │               │
│           │               ├── ApiService
│           │               ├── CropResponse
│           │               ├── CustomSpinnerAdapter
│           │               ├── Home
│           │               ├── LoginActivity
│           │               ├── MainActivity
│           │               ├── Market
│           │               ├── Profile
│           │               └── ...
│           │
│           ├── res/
│           │   ├── layout/
│           │   ├── drawable/
│           │   ├── mipmap/
│           │   └── values/
│           │
│           └── AndroidManifest.xml
│
├── gradle/
├── build.gradle
├── settings.gradle
├── .gitignore
└── README.md
```

---

# 📸 Application Screenshots

Create a `screenshots` folder in the root of the repository:

```text
screenshots/
│
├── login.png
├── home.png
├── crop-detection.png
├── disease-result.png
├── remedies.png
├── market.png
├── labour.png
└── profile.png
```

Then add them to this section.

### 🔐 Login

<p align="center">
<img src="screenshots/login.png" width="250"/>
</p>

### 🏠 Home Dashboard

<p align="center">
<img src="screenshots/home.png" width="250"/>
</p>

### 🌱 Crop Detection

<p align="center">
<img src="screenshots/crop-detection.png" width="250"/>
</p>

### 🤖 Disease Result

<p align="center">
<img src="screenshots/disease-result.png" width="250"/>
</p>

### 💊 Remedies

<p align="center">
<img src="screenshots/remedies.png" width="250"/>
</p>

### 📈 Market

<p align="center">
<img src="screenshots/market.png" width="250"/>
</p>

### 👷 Labour

<p align="center">
<img src="screenshots/labour.png" width="250"/>
</p>

### 👤 Profile

<p align="center">
<img src="screenshots/profile.png" width="250"/>
</p>

---

# 🎯 Project Objectives

The main objectives of Farmer Friend are:

- 🌱 Make agricultural technology more accessible
- 🔍 Assist farmers in identifying crop diseases
- 📖 Provide understandable disease information
- 💊 Provide multiple remedy categories
- 📱 Bring farming-related services into one application
- ⚡ Reduce the complexity of accessing agricultural information
- 🤖 Build a foundation for future AI-powered agricultural services

---

# 🚀 Future Enhancements

The project can be extended with:

- 📊 Live agricultural market prices
- 🌤️ More detailed weather forecasting
- 🗺️ Location-based services
- 👨‍🌾 Farmer-to-expert communication
- 👷 Location-based labour matching
- ⭐ Labour ratings and reviews
- 🌾 More crop and disease classes
- 📱 Offline-first crop detection
- 🌐 Multi-language support
- 🗣️ Voice-based agricultural assistant
- 📈 Crop price prediction
- 🧠 More advanced AI-based crop diagnosis
- 🔔 Weather and crop-disease alerts

---

# 🔒 Security

Do **not** commit private credentials, API keys, passwords or other
sensitive information to a public GitHub repository.

### Keep these files/information private

```text
google-services.json
local.properties
.env
API keys
Private credentials
Passwords
Access tokens
```

Use environment variables or secure configuration mechanisms for
sensitive information.

---

# 📋 Project Information

| Category | Details |
|---|---|
| 🌾 Project | Farmer Friend |
| 📱 Platform | Android |
| 🎯 Domain | Agriculture + Artificial Intelligence |
| 💻 Primary Language | Kotlin |
| 🤖 AI | Gemini API |
| 🧠 Machine Learning | TensorFlow Lite |
| 🔐 Authentication | Firebase |
| 🖥️ Backend | Python + Flask |
| 🌐 Networking | Retrofit + REST APIs |

---

# 🤝 Contributing

Contributions and suggestions are welcome.

### Steps

```bash
# Fork the repository

# Clone your fork
git clone https://github.com/YOUR_USERNAME/Farmer-Friend.git

# Create a feature branch
git checkout -b feature/your-feature

# Make your changes

# Commit your changes
git add .
git commit -m "Add new feature"

# Push your branch
git push origin feature/your-feature
```

Then create a **Pull Request**.

---

# ⭐ Support

If you find this project useful or interesting,
consider giving the repository a ⭐ **Star**.

---

<p align="center">

## 🌾 Farmer Friend

### Technology for smarter and more accessible farming.

**Built with ❤️ using Android, AI & Machine Learning.**

</p>
