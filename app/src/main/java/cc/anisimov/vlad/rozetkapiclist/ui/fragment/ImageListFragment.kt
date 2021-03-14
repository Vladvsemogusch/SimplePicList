package cc.anisimov.vlad.rozetkapiclist.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import cc.anisimov.vlad.rozetkapiclist.R
import cc.anisimov.vlad.rozetkapiclist.domain.model.Image
import cc.anisimov.vlad.rozetkapiclist.domain.viewmodel.ImageListViewModel
import cc.anisimov.vlad.rozetkapiclist.ui.common.BaseFragment
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.davidea.viewholders.FlexibleViewHolder
import kotlinx.android.synthetic.main.fragment_image_list.*
import kotlinx.android.synthetic.main.loading_overlay.*
import kotlinx.android.synthetic.main.toolbar.*

@AndroidEntryPoint
class ImageListFragment : BaseFragment() {
    private val viewModel: ImageListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_image_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar(toolbar, getString(R.string.gallery))
        viewModel.start()
        setupErrorHandling(viewModel.oError)
        setupLoading(viewModel.oLoading, loadingOverlay)
        setupList()
    }

    private fun setupList() {
        rvImageList.layoutManager = StaggeredGridLayoutManager(2, VERTICAL)
        val listAdapter = FlexibleAdapter<ImageAdapterItem>(null)
        rvImageList.adapter = listAdapter
        viewModel.oImageList.observe(viewLifecycleOwner) { imageList ->
            if (imageList == null || imageList.isEmpty()) {
                return@observe
            }
            val adapterItems = imageList.map { ImageAdapterItem(it) }
            listAdapter.addItems(0, adapterItems)
        }
    }

    class ImageAdapterItem(private val image: Image) :
        AbstractFlexibleItem<ImageAdapterItem.PhotoViewHolder>() {

        override fun equals(other: Any?): Boolean {
            if (other is ImageAdapterItem) {
                return this.image.id == other.image.id
            }
            return false
        }

        override fun getLayoutRes(): Int {
            return R.layout.item_image
        }

        override fun createViewHolder(
            view: View,
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
        ): PhotoViewHolder {
            return PhotoViewHolder(view, adapter)
        }

        override fun bindViewHolder(
            adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
            holder: PhotoViewHolder,
            position: Int,
            payloads: MutableList<Any>?
        ) {
            holder.ivThumbnail.load(image.url)
        }

        override fun hashCode(): Int {
            return image.id.hashCode()
        }

        class PhotoViewHolder(view: View, adapter: FlexibleAdapter<*>) :
            FlexibleViewHolder(view, adapter) {
            val ivThumbnail: ImageView = view.findViewById(R.id.ivThumbnail)
        }
    }
}