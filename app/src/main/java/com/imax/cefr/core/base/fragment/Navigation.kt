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
    val fragmentInStack = this.findFragmentByTag(fragment.javaClass.simpleName)
    val transaction = this.beginTransaction()

    val fragments = this.fragments
    for (existingFragment in fragments) {
        if (existingFragment.isVisible) {
            transaction.hide(existingFragment)
        }
    }

    if (fragmentInStack == null) {

        transaction.add(fragmentContainer, fragment, fragment.javaClass.simpleName)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }
}
