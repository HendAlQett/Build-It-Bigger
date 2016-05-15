# Build-It-Bigger

##Android project to tell jokes:

  * Java library to supply jokes.
  * Android library to display jokes passed to it as intent extras. 
  * Backend (Google Cloud Endpoints Module)to supply jokes from the Java library.
  * Project loads jokes from GCE module via AsyncTask
  * Paid/Free flavors.
  * AdMob in free version.
  * Interstitial Ads in free verion.
  * Removing unused dependencies from paid version.
  * Android test case to verify that AsyncTask is indeed loading jokes.
  * Gradle task to:
   * Launche the GCE local development server.
   * Run all server.
   * Shutdown the server again.

##Why this Project?

As Android projects grow in complexity, it becomes necessary to customize the behavior of the Gradle build tool, allowing automation of repetitive tasks. Particularly, factoring functionality into libraries and creating product flavors allow for much bigger projects with minimal added complexity.
