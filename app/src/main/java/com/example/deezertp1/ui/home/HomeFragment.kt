package com.example.deezertp1.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.deezertp1.databinding.FragmentHomeBinding
import com.example.deezertp1.service.DeezerService
import com.example.deezertp1.service.data.CallbackSearchAuthor
import com.example.deezertp1.service.data.DataSearchAuthor
import com.example.deezertp1.ui.RecyclerAuthorAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel =
        //    ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerviewArtist.layoutManager = LinearLayoutManager(context)
        binding.recyclerviewArtist.adapter = RecyclerAuthorAdapter()

        val artist = arguments?.getString("artist")
        artist?.let { // if (query != null)
            DeezerService().searchAuthor(artist, object : CallbackSearchAuthor() {
                override fun fireDataSearchAuthor(data: DataSearchAuthor) {
                    Log.d(TAG, "data: $data")
                    binding.recyclerviewArtist.post {
                        binding.recyclerviewArtist.layoutManager = LinearLayoutManager(context)
                        binding.recyclerviewArtist.adapter = RecyclerAuthorAdapter(data.data)
                    }
                }
            })
        }
        /* TODO: rechercher des albums lorsqu'on clique sur auteur
        RecyclerAuthorAdapter().setOnClickListener(object :
            RecyclerAuthorAdapter.OnClickListener {
            override fun onClick(position: Int, model: Author) {
                val album = arguments?.getString("artist")
                album?.let { // if (query != null)
                    DeezerService().searchAlbumsOfAuthor(album, object: CallbackSearchAlbums() {
                        override fun fireDataSearchAlbums(data: DataSearchAlbums) {
                            Log.d(TAG, "data: $data")
                            binding.recyclerviewArtist.post {
                                binding.recyclerviewArtist.layoutManager = LinearLayoutManager(context)
                                binding.recyclerviewArtist.adapter = RecyclerAlbumAdapter(data.data)
                            }
                        }
                    })
                }
            }
        })
        */

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}