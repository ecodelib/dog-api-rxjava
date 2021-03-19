package ru.ric_kos.dogapirxjavaexample.view

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import ru.ric_kos.dogapirxjavaexample.viewmodel.MessageViewModel
import ru.ric_kos.dogapirxjavaexample.R
import ru.ric_kos.dogapirxjavaexample.viewmodel.ViewModelFactory
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import ru.ric_kos.dogapirxjavaexample.model.domain.Result


class MessageFragment : Fragment() {

    lateinit var imageView : ImageView
    lateinit var buttonAnswer : Button
    companion object {
        fun newInstance() = MessageFragment()
    }


    private val messageViewModel by lazy {
        ViewModelProvider (viewModelStore,
                ViewModelFactory()
        ).get(MessageViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLiveData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.ans_fragment, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView  = view.findViewById(R.id.imageView)
        buttonAnswer = view.findViewById(R.id.getButton)

        buttonAnswer.setOnClickListener{
            messageViewModel.loadMessage()
        }


    }

    private fun observeLiveData() {
        activity?.let {
            messageViewModel.answer.observe(it, Observer { result ->
                when (result) {
                    is Result.Success -> {
                        loadFromURL(result.value.imageUrl, requireContext(), imageView)
                    }
                    is Result.Failure -> {
                        loadFromLocal(R.drawable.n02085936_6671,requireContext(),imageView);
                    }
                }
            })
        }
    }
    private fun loadFromURL(url: String, context: Context, imageview: ImageView){
        Glide
            .with(context)
            .load(url)
            .listener(object: RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadFromLocal(R.drawable.n02085936_6671,requireContext(),imageView);
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }
            })
            .into(imageview)
    }
    private fun loadFromLocal(resId : Int, context : Context,imageview: ImageView){
        Glide
                .with(context)
                .load(resId)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        //добавить бы snackbar
                        return false
                    }
                }).into(imageview)

    }
}