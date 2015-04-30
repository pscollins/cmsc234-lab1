package org.scaloid.hello

import android.content.Intent
import android.view.View
import org.scaloid.common._
import android.graphics.Color

class HelloScaloidActivity extends SActivity {
  onCreate {
    var box: SEditText = null
    contentView = new SVerticalLayout {
      STextView(R.string.whats_your_name) textSize 75.dip
      box = SEditText() hint "John Smith"
      SButton("Done", startActivity(SIntent[Greeting] putExtra("name", box.getText.toString)))
    }
  }
}
