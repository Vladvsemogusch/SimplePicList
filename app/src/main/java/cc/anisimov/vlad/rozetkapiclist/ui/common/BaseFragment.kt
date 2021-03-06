package cc.anisimov.vlad.rozetkapiclist.ui.common

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import cc.anisimov.vlad.rozetkapiclist.R
import cc.anisimov.vlad.rozetkapiclist.domain.utility.SingleLiveEvent

open class BaseFragment : Fragment() {

    protected fun setupToolbar(
        toolbarView: Toolbar,
        title: String,
        enableBackButton: Boolean = true
    ) {
        val activity = requireActivity() as AppCompatActivity
        activity.setSupportActionBar(toolbarView)
        activity.title = title
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(enableBackButton)
    }

    protected fun showSimpleDialog(errorText: String?) {
        AlertDialog.Builder(requireContext()).setTitle("Alert")
            .setMessage(errorText)
            .setTitle(R.string.error_title)
            .setPositiveButton(
                "OK"
            ) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    protected fun setupErrorHandling(oError: SingleLiveEvent<String?>) {
        oError.observe(viewLifecycleOwner) { errorText ->
            if (errorText != null) {
                showSimpleDialog(errorText)
            }
        }
    }

    protected fun setupLoading(oLoading: LiveData<Boolean>, loadingOverlay: View) {
        oLoading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                loadingOverlay.visibility = View.VISIBLE
            } else {
                loadingOverlay.visibility = View.GONE
            }
        }
    }
}