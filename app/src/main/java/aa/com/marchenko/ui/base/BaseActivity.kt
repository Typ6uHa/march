package aa.com.marchenko.ui.base

import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import aa.com.marchenko.ui.base.BaseView

abstract class BaseActivity : MvpAppCompatActivity(), BaseView {
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}