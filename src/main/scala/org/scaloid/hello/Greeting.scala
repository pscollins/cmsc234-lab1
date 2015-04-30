package org.scaloid.hello

import collection.JavaConversions._
import android.app.Activity
import android.content.Context
import android.location.{Location, LocationListener, LocationManager}
import android.os.Bundle
import org.scaloid.common._

/**
 * Created by patrick on 4/6/15.
 */
class Greeting extends SActivity {
  val LOCATION_DEFAULT = "Location not available."

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    var latText: STextView = null
    var lonText: STextView = null

    contentView = new SVerticalLayout {
      STextView(s"${R.string.prompt.r2String}! ${getIntent.getStringExtra("name")}") textSize 75.dip
      latText = STextView(LOCATION_DEFAULT) textSize 50.dip
      lonText = STextView(LOCATION_DEFAULT) textSize 50.dip
      SButton("Back", startActivity[HelloScaloidActivity])
    }

    val locListener = new LocationListener {
      override def onLocationChanged(location: Location): Unit = {
        latText setText s"Latitude: ${location.getLatitude.toString}"
        lonText setText s"Longitude: ${location.getLongitude.toString}"
      }

      override def onProviderEnabled(s: String): Unit = ()
      override def onStatusChanged(s: String, i: Int, bundle: Bundle): Unit = ()
      override def onProviderDisabled(s: String): Unit = ()
    }

    locationManager getAllProviders() foreach(locationManager.requestLocationUpdates(_, 0, 0, locListener))
  }
}
