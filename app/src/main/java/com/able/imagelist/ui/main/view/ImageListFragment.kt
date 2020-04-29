package com.able.imagelist.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.able.imagelist.R
import com.able.imagelist.data.api.ApiHelper
import com.able.imagelist.data.api.RetrofitBuilder
import com.able.imagelist.data.model.ImagesModel
import com.able.imagelist.ui.base.ViewModelFactory
import com.able.imagelist.ui.main.adapter.ImageAdapter
import com.able.imagelist.ui.main.viewmodel.GROUP_NUMBER
import com.able.imagelist.ui.main.viewmodel.MainViewModel
import com.able.imagelist.util.Status
import com.scwang.smartrefresh.header.MaterialHeader
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import kotlinx.android.synthetic.main.fragment_imagelist.*

class ImageListPageFragment : Fragment() {

    private lateinit var mRv: RecyclerView
    private lateinit var mRefreshLayout: SmartRefreshLayout
    private lateinit var mViewModel: MainViewModel
    private val position = lazy { requireArguments().getInt("position") }
    private val mAdapter = lazy {
        ImageAdapter(ArrayList())
    }

    //页码
    private var page: Int = 0
    private var oldPage: Int = 0
    private var isRefresh: Boolean = true;
    private var isLoadmore: Boolean = false;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_imagelist, container, false)
        mRv = view.findViewById(R.id.rv)
        mRefreshLayout = view.findViewById(R.id.refreshLayout)
        mRefreshLayout.setRefreshHeader(MaterialHeader(context))
        mRefreshLayout.setRefreshFooter(ClassicsFooter(context))
        //刷新
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            isLoadmore = false
            page = position.value + 1
            mViewModel.setPage(page)
        }
        //加载更多
        mRefreshLayout.setOnLoadMoreListener {
            page += GROUP_NUMBER
            isRefresh = false
            isLoadmore = true
            mViewModel.setPage(page)
        }
        initRv()
        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)

        setupObservers()


    }

    private fun setupObservers() {
        //创建时请求数据
        page = position.value + 1
        mViewModel.setPage(page)

        mViewModel.images.observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        val data: ImagesModel = it.data!!
                        val map = data.data.map { it1 -> it1.url }
                        Log.i("页码", "$page")
//                        page = position.value + 1
//                        mViewModel.setPage(page + GROUP_NUMBER)


//                        mAdapter.value.setNewInstance(ArrayList(map))
//                        mRefreshLayout.finishRefresh()
                        if (isRefresh) {
                            mAdapter.value.setNewInstance(ArrayList(map))
                            mRefreshLayout.finishRefresh()
                        }
                        if (isLoadmore) {
                            mAdapter.value.addData(ArrayList(map))
                            mRefreshLayout.finishLoadMore()
                        }
                        if (map.isNotEmpty()) {
                            page = oldPage
                        }


                    }
                    Status.ERROR -> {

                        Toast.makeText(context, "错误:$it", Toast.LENGTH_SHORT).show()
                    }
                    Status.LOADING -> {
//                        Toast.makeText(context, "加载中", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun initRv() {
        mRv.setHasFixedSize(true);
        mRv.layoutManager = GridLayoutManager(context, 2);
        mRv.adapter = mAdapter.value;
        mAdapter.value.setOnItemClickListener { adapter, view, position ->
            val string = adapter.getItem(position) as String
            val intent = Intent(context, ShowImagerActivity::class.java)
            intent.putExtra("url", string)
            startActivity(intent)
        }

    }


    companion object {

        fun create(position: Int): ImageListPageFragment {
            val ivListFragment = ImageListPageFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            ivListFragment.arguments = bundle
            return ivListFragment
        }
    }
}