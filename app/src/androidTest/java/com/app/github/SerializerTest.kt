package com.app.github

import android.graphics.Insets.add
import com.app.github.data.model.ListUser
import com.app.github.data.model.User
import org.json.JSONArray


fun json_response(): ArrayList<User> {
    return listUser
}


val listUser = ArrayList<User>().apply {
    add(User(
            "https://avatars.githubusercontent.com/u/1?v=4",
            "https://api.github.com/users/mojombo/events{/privacy}",
            "https://api.github.com/users/mojombo/followers",
            "https://api.github.com/users/mojombo/following{/other_user}",
            "https://api.github.com/users/mojombo/gists{/gist_id}",
            "",
            "https://github.com/mojombo",
            1,
            "mojombo",
            "MDQ6VXNlcjE=",
            "https://api.github.com/users/mojombo/orgs",
            "https://api.github.com/users/mojombo/received_events",
            "https://api.github.com/users/mojombo/repos",
            false,
            "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            "https://api.github.com/users/mojombo/subscriptions",
            "User",
            "https://api.github.com/users/mojombo"
        ))
    add(User(
        "https://avatars.githubusercontent.com/u/1?v=4",
        "https://api.github.com/users/mojombo/events{/privacy}",
        "https://api.github.com/users/mojombo/followers",
        "https://api.github.com/users/mojombo/following{/other_user}",
        "https://api.github.com/users/mojombo/gists{/gist_id}",
        "",
        "https://github.com/mojombo",
        2,
        "mojombo",
        "MDQ6VXNlcjE=",
        "https://api.github.com/users/mojombo/orgs",
        "https://api.github.com/users/mojombo/received_events",
        "https://api.github.com/users/mojombo/repos",
        false,
        "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
        "https://api.github.com/users/mojombo/subscriptions",
        "User",
        "https://api.github.com/users/mojombo"
    ))
    add(User(
        "https://avatars.githubusercontent.com/u/1?v=4",
        "https://api.github.com/users/mojombo/events{/privacy}",
        "https://api.github.com/users/mojombo/followers",
        "https://api.github.com/users/mojombo/following{/other_user}",
        "https://api.github.com/users/mojombo/gists{/gist_id}",
        "",
        "https://github.com/mojombo",
        3,
        "mojombo",
        "MDQ6VXNlcjE=",
        "https://api.github.com/users/mojombo/orgs",
        "https://api.github.com/users/mojombo/received_events",
        "https://api.github.com/users/mojombo/repos",
        false,
        "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
        "https://api.github.com/users/mojombo/subscriptions",
        "User",
        "https://api.github.com/users/mojombo"
    ))
}
