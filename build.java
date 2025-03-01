plugins {
    id 'com.android.application'
    id 'com.google.gms.google-services'
}

android {
    compileSdkVersion 33

    defaultConfig {
        applicationId "com.inhatc.project_android"
        minSdkVersion 30
        targetSdkVersion 33
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled true
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    dataBinding {
        enabled = true
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_11
        targetCompatibility JavaVersion.VERSION_11
    }

    buildToolsVersion '30.0.3'
}

dependencies {
    implementation 'com.google.android.gms:play-services-safetynet:18.0.1'
    implementation platform('com.google.firebase:firebase-bom:32.1.0')
    implementation 'com.google.firebase:firebase-analytics'
    implementation 'androidx.appcompat:appcompat:1.4.2'
    implementation 'com.google.android.material:material:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.4'
    implementation 'androidx.annotation:annotation:1.6.0'
    implementation 'com.google.firebase:firebase-database:20.0.2'
    testImplementation 'junit:junit:4.12'
    implementation 'com.amitshekhar.android:android-networking:1.0.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'
}

google-services
{
  "project_info": {
    "project_number": "978113920434",
    "project_id": "android31-1685951287960",
    "storage_bucket": "android31-1685951287960.appspot.com"
  },
  "client": [
    {
      "client_info": {
        "mobilesdk_app_id": "1:978113920434:android:31c8d1e74b666cb9b038ab",
        "android_client_info": {
          "package_name": "com.inhatc.project_android"
        }
      },
      "oauth_client": [
        {
          "client_id": "978113920434-ts29jhpuutsdg68itcrj1sjcqntp5e0j.apps.googleusercontent.com",
          "client_type": 3
        }
      ],
      "api_key": [
        {
          "current_key": "AIzaSyCJblHQwnhSyX2qk3OFHWzWJ5SODmulREo"
        }
      ],
      "services": {
        "appinvite_service": {
          "other_platform_oauth_client": [
            {
              "client_id": "978113920434-ts29jhpuutsdg68itcrj1sjcqntp5e0j.apps.googleusercontent.com",
              "client_type": 3
            }
          ]
        }
      }
    }
  ],
  "configuration_version": "1"
}
