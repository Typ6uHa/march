package aa.com.marchenko.ui.selected

import aa.com.marchenko.data.storage.model.Course
import aa.com.marchenko.ui.base.BaseView

interface SelectedView : BaseView {
    fun submitList(list: List<Course>)
}