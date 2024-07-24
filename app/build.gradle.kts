plugins {
    alias(libs.plugins.androidApplication222222)
    alias(libs.plugins.jetbrainsKotlinAndroid)
//    alias(libs.plugins.aspectjx)
}

android {
    namespace = "com.aben.debounceclickdemo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aben.debounceclickdemo"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

//// ajx配置
//aspectjx {
//    // 是否debug，开启后会输出织入信息等相关日志（3.3.0版本新增）
//    debug = true
//    // 是否启动
//    enabled = true
//    // 移除测试
//    exclude("com.lancewu.aspectj.AppExcludeTest")
//    // 移除kotlin相关，编译错误和提升速度
//    exclude ("kotlin.jvm"," 'kotlin.internal'")
//    exclude ("kotlinx.coroutines.internal", "kotlinx.coroutines.android")
//}


dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
//    implementation(libs.aspectjrt)
//    implementation(libs.aspectjtools)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}