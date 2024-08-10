package com.imax.cefr.core.base.fragment

import androidx.annotation.IdRes
import androidx.annotation.NavigationRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment

fun FragmentManager.changeNavGraph(@IdRes navHostView: Int, @NavigationRes navigation: Int) {
    val navHostFragment = this.findFragmentById(navHostView) as NavHostFragment
    val inflater = navHostFragment.navController.navInflater
    val graph = inflater.inflate(navigation)
    navHostFragment.navController.graph = graph
}


fun FragmentManager.addFragment(
    @IdRes fragmentContainer: Int,
    fragment: Fragment,
    fragmentTag: String
){
    val transaction = this.beginTransaction()
    transaction.add(fragment, fragmentTag)
    transaction.commit()
}

fun FragmentManager.replaceFragment(
    @IdRes fragmentContainer: Int,
    fragment: Fragment
) {
    val transaction = this.beginTransaction()
    transaction.replace(fragmentContainer, fragment)
    transaction.commit()
}

fun FragmentManager.addFragmentToBackStack(
    @IdRes fragmentContainer: Int,
    fragment: Fragment
) {
    val fragmentPopped = this.popBackStackImmediate(fragment.tag, 0)
    if (!fragmentPopped && this.findFragmentByTag(fragment.tag) == null) {
        val transaction = this.beginTransaction()
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.add(fragmentContainer, fragment)
        transaction.commit()
    }
}
