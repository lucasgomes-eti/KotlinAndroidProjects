package com.lucasgomes.gitrepositories

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RepositoriesListActivity : AppCompatActivity() {

    val listRepositories : ListView by lazy { findViewById(R.id.listViewRepositories) as ListView }

    lateinit var repositoryAdapter : ArrayAdapter<String>
    var repositories = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repositories_list)

        repositoryAdapter = ArrayAdapter(this, R.layout.repository_item, repositories)
        listRepositories.adapter = repositoryAdapter

        val api = GitApi()

        api.loadRepositories()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe(
                        {
                            (id, name, description, language) ->
                            repositories.add("Nome do projeto: $name \n Descrição: $description \n Linguagem: $language")
                        },
                        {
                            e ->
                            e?.printStackTrace()
                        },
                        {
                            repositoryAdapter?.notifyDataSetChanged()
                        }
                )
    }
}