plugins {
    id("com.android.application")
}

android {
    namespace = "com.jnu.student"
    compileSdk = 33

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    defaultConfig {
        applicationId = "com.jnu.student"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")

    implementation("com.google.android.material:material:1.8.0")
    implementation ("com.tencent.map:tencent-map-vector-sdk:4.3.4")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    implementation("androidx.test:monitor:1.6.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.json:json:20231013")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Required -- JUnit 4 framework
    testImplementation ("junit:junit:4.13.1")

    testImplementation ("androidx.test:core-ktx:1.3.0")
    testImplementation ("androidx.test.ext:junit-ktx:1.1.2")

// Robolectric environment
    testImplementation ("org.robolectric:robolectric:4.4")

// Optional -- truth
    testImplementation ("androidx.test.ext:truth:1.3.0")
    testImplementation ("com.google.truth:truth:1.0")

// Optional -- Mockito framework
    testImplementation ("org.mockito:mockito-core:3.3.3")
}