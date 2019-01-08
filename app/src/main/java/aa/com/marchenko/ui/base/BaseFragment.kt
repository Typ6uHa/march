package aa.com.marchenko.ui.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment

abstract class BaseFragment : MvpAppCompatFragment(), BaseView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        layoutId?.let {
            return inflater.inflate(it, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    abstract fun getPresenter(): BasePresenter<*>?

    @get:LayoutRes
    abstract val layoutId: Int?
}