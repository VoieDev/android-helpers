package voiedev

import android.app.Activity
import android.content.Context
import androidx.annotation.StringRes
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

const val REQ_GOOGLE_SIGN_IN = 4664

fun Context.googleAuthClient(@StringRes resId: Int): GoogleSignInClient {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(this.getString(resId))
        .requestEmail()
        .build()

    return GoogleSignIn.getClient(this, gso)
}

fun GoogleSignInClient.signIn(context: Activity) =
    context.startActivityForResult(signInIntent, REQ_GOOGLE_SIGN_IN)