package aa.com.marchenko.ui.main

import aa.com.marchenko.R
import aa.com.marchenko.ui.base.BaseActivity
import aa.com.marchenko.ui.courses.CoursesFragment
import aa.com.marchenko.ui.recomendate.RecomendateFragment
import aa.com.marchenko.ui.selected.SelectedFragment
import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    private lateinit var currentFragmentTag: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            firstInitialization()
        } else {
            currentFragmentTag = savedInstanceState["currentFragmentTag"] as String
        }

        navigation.setOnNavigationItemSelectedListener { item ->

            val newFragmentTag = when (item.itemId) {
                R.id.navigationCourses -> CoursesFragment::class.java.canonicalName
                R.id.navigationRecomendate -> RecomendateFragment::class.java.canonicalName
                R.id.navigationSelected -> SelectedFragment::class.java.canonicalName
                else -> throw IllegalStateException("unknown item id ${item.itemId}")
            }

            val newFragment = supportFragmentManager.findFragmentByTag(newFragmentTag)!!

            if (currentFragmentTag !== newFragmentTag) {
                val currentFragment = supportFragmentManager.findFragmentByTag(currentFragmentTag)!!

                currentFragment.setHasOptionsMenu(false)

                supportFragmentManager.beginTransaction()
                        .hide(currentFragment)
                        .show(newFragment)
                        .commit()

                newFragment.setHasOptionsMenu(true)

                currentFragmentTag = newFragmentTag
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    private fun firstInitialization() {

        val coursesFragment = CoursesFragment()
        val recomendateFragment = RecomendateFragment()
        val selectedFragment = SelectedFragment()

        coursesFragment.setHasOptionsMenu(true)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, coursesFragment, coursesFragment.javaClass.canonicalName)
        transaction.add(R.id.container, recomendateFragment, recomendateFragment.javaClass.canonicalName)
        transaction.add(R.id.container, selectedFragment, selectedFragment.javaClass.canonicalName)
        transaction.hide(recomendateFragment)
        transaction.hide(selectedFragment)
        transaction.commit()

        currentFragmentTag = coursesFragment.javaClass.canonicalName!!
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("currentFragmentTag", currentFragmentTag)
    }
}