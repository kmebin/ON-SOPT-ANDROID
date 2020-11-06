# SOPT-27th-Android

</br>

* [1주차 과제](#memo-1주차-과제-2020-10-16--2020-10-21)

* [2주차 과제](#memo-2주차-과제-2020-10-29)

* [3주차 과제](#memo-3주차-과제-2020-11-06)

</br>

## :memo: 1주차 과제 (2020-10-16 / 2020-10-21)

> :computer: **필수 과제 (2020-10-16)**

• 로그인 화면

<img width="300" alt="login" src="https://user-images.githubusercontent.com/72112845/97577084-4dbf4600-1a32-11eb-9f92-00ede1f8110d.png">

• 빈칸이 있는 경우 회원가입 화면

<img width="300" alt="signup" src="https://user-images.githubusercontent.com/72112845/97577223-7a735d80-1a32-11eb-9e5f-0ceeb0768f21.png">

</br>

> :computer: **성장 과제 1 (2020-10-16)**

• 회원가입 완료 후 이전 로그인 화면으로 돌아오기

<img width="300" alt="login2" src="https://user-images.githubusercontent.com/72112845/97577308-98d95900-1a32-11eb-8d94-a09a536a7684.png">

</br>

> :computer: **성장 과제 2 (2020-10-21)**

• 자동 로그인 화면

<img width="300" alt="login3" src="https://user-images.githubusercontent.com/72112845/96689940-0693de00-13be-11eb-9ad2-00b8feb97899.png"> <img width="300" alt="login4" src="https://user-images.githubusercontent.com/72112845/96690099-3ba03080-13be-11eb-8fe3-ee089d21550c.png">

</br>

## :memo: 코드 설명

:bulb: **startActivityForResult**

> LoginActivity.kt

* 회원가입 버튼을 클릭했을 때, LoginActivity에서 SignUpActivity로 이동합니다.

* SignUpActivity가 종료되면서 데이터를 받아오기 위해 `startActivityForResult()`를 사용했습니다.

```Kotlin
btn_sign_up.setOnClickListener {
    val intent = Intent(this, SignUpActivity::class.java)
    startActivityForResult(intent, 111)
}

```

</br>

> SignUpActivity.kt

* 회원가입 버튼을 클릭했을 때, EditTextView에 값이 하나라도 없을 경우 "빈칸이 있습니다."라는 **ToastMessage**를 띄웁니다.

* 회원가입 버튼을 클릭했을 때, 모든 EditTextView에 값이 있는 경우 "회원가입이 완료되었습니다."라는 **ToastMessage**를 띄우고, Id와 Password 값을 넣어줍니다.

* `setResult()`로 값을 설정해주고, `finish()`로 현재 액티비티를 종료한 후 LoginActivity로 돌아옵니다.

```Kotlin
btn_sign_up.setOnClickListener {

    var inputName = et_name.text.toString()
    var inputId = et_id.text.toString()
    var inputPw = et_pw.text.toString()

    if (inputName.isEmpty() || inputId.isEmpty() || inputPw.isEmpty())
        Toast.makeText(this, "빈칸이 있습니다.", Toast.LENGTH_SHORT).show()
    else {
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show()

        val intent = Intent()
        intent.putExtra("id", et_id.text.toString())
        intent.putExtra("password", et_pw.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
```

</br>

> LoginActivity.kt

* LoginActivity로 돌아오면서 `onActivityResult()`가 실행됩니다.

* 회원가입 화면에서 입력했던 아이디와 비밀번호를 받아와 로그인 화면에 입력되어 있게 합니다.

```Kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if (resultCode == Activity.RESULT_OK && requestCode == 111) {
        var userId = data!!.getStringExtra("id")
        var userPw = data.getStringExtra("password")

        editText_id.setText(userId)
        editText_pw.setText(userPw)
    }
}
```

</br>

:bulb: **SharedPreferences**

> LoginActivity.kt

* `SharedPreferences()`는 **Map** 형태로 간단한 값을 저장할 수 있습니다.

* **SharedPreferences.Editor**는 SharedPreferences 개체의 값을 수정하는데 사용되는 인터페이스입니다.

```Kotlin
val sharedPref: SharedPreferences = getSharedPreferences("pref", Context.MODE_PRIVATE)
val sharedEdit = sharedPref.edit()
```

</br>

* 로그인 버튼을 클릭했을 때, "반갑습니다."라는 ToastMessage를 띄우고 HomeActivity로 이동합니다.

* Editor 객체에 `putString()`을 통해 Map 형태로 데이터를 저장합니다.

* 반드시 `apply()` 또는 `commit()`을 해줍니다.

```Kotlin
btn_login.setOnClickListener {
    Toast.makeText(this, "반갑습니다.", Toast.LENGTH_SHORT).show()

    sharedEdit.putString("Id", editText_id.text.toString())
    sharedEdit.putString("Password", editText_pw.text.toString())
    sharedEdit.apply()

    val intent = Intent(this, HomeActivity::class.java)
    startActivity(intent)
}
```

</br>

* `getString()`을 통해 해당 Key 값을 가진 string을 가져옵니다. 존재하지 않는다면 default 값을 가져옵니다.

```Kotlin
var idValue = sharedPref.getString("Id", "")
var pwValue = sharedPref.getString("Password", "")

editText_id.setText(idValue)
editText_pw.setText(pwValue)
```

</br>

* SharedPreferences 안에 값이 저장되어 있다면, 자동 로그인 되었다는 ToastMessage를 띄우고 HomeActivity로 이동합니다.

* 앱을 재시작했을 때 자동 로그인이 됩니다.

```Kotlin
if (idValue.toString().isNotBlank() && pwValue.toString().isNotBlank()) {
    Toast.makeText(this, "${idValue.toString()}님 자동 로그인 되었습니다.", Toast.LENGTH_SHORT).show()

    val intent = Intent(this, HomeActivity::class.java)
    startActivity(intent)
}
```

</br></br>

## :memo: 2주차 과제 (2020-10-29)

> :computer: **필수 과제 (2020-10-29)**

• 로그인했을 때의 화면과 각 아이템을 클릭했을 때의 상세 화면

<img width="300" alt="recyclerview" src="https://user-images.githubusercontent.com/72112845/97457743-628dd200-197d-11eb-90b6-f3292c56c93b.png"> <img width="300" alt="detail" src="https://user-images.githubusercontent.com/72112845/97457866-7f2a0a00-197d-11eb-84eb-6a68f558011e.png">

</br>

> :computer: **성장 과제 1 (2020-10-29)**

• 격자 형태(GridLayout)로 바뀐 화면

<img width="300" alt="gridLayout" src="https://user-images.githubusercontent.com/72112845/97452444-18562200-1978-11eb-9646-a67659795137.png">

</br>

> :computer: **성장 과제 2 (2020-10-29)**

• 아이템 이동 및 삭제 화면

<img width="300" alt="move" src="https://user-images.githubusercontent.com/72112845/97577631-04bbc180-1a33-11eb-9602-ae5fd23a414a.gif"> <img width="300" alt="remove" src="https://user-images.githubusercontent.com/72112845/97577814-4187b880-1a33-11eb-92e7-491b7eb2d58e.gif">

</br>

## :memo: 코드 설명

:bulb: **RecyclerView**

> ProfileData.kt

* 아이템에 대한 데이터 객체를 만들기 위해 **data class**를 생성합니다.

```Kotlin
data class ProfileData(
    val title : String,
    val subTitle : String,
    val contents : String
)
```

</br>

> ProfileViewHolder.kt

* **Adapter**에서 전달받은 데이터를 layout에 넣어줍니다. 이를 **Bind**한다고 표현합니다.

* `onBind()`는 Adapter에서 호출되고, 실질적으로 데이터를 요소들에 넣어주는 메소드입니다.

```Kotlin
class ProfileViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    private val title : TextView = itemView.findViewById(R.id.tv_title)
    private val subTitle : TextView = itemView.findViewById(R.id.tv_subtitle)

    fun onBind(data : ProfileData){
        title.text = data.title
        subTitle.text = data.subTitle
    }
}
```

</br>

> ProfileAdapter.kt

* **Adapter**에는 `onCreateViewHolder()`, `getItemCount()`, `onBindViewHolder()` 3가지 메소드를 반드시 오버라이드 해줘야 합니다.

* setOnClickListener를 통해 아이템을 클릭하면 상세 화면으로 이동하게 했습니다.

```Kotlin
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {

    val view = LayoutInflater.from(context).inflate(R.layout.profile_item_list, parent, false)

    return ProfileViewHolder(view).apply {
        // item을 클릭하면 상세 화면으로 이동
        itemView.setOnClickListener {
            val curPosition : Int = adapterPosition
            val profile : ProfileData = data.get(curPosition)
            val intent = Intent(context, ProfileDetailActivity::class.java)

            intent.putExtra("title", profile.title)
            intent.putExtra("subTitle", profile.subTitle)
            intent.putExtra("contents", profile.contents)
            context.startActivity(intent)
        }
    }
}

override fun getItemCount(): Int = data.size

override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
    holder.onBind(data[position])
}
```

</br>

:bulb: **GridLayout**

> ProfileActivity.kt

* 우선 RecyclerView의 배치 방향을 **LinearLayoutManager**로 설정하였습니다.

* **profileAdapter**에 리스트로 보여줄 데이터들을 넣어줍니다.

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_profile)

    profileAdapter = ProfileAdapter(this)

    rv.adapter = profileAdapter
    rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    profileAdapter.data = mutableListOf(
        ProfileData("이름", "김희빈", "안녕하세요. 김희빈입니다."),
        ProfileData("나이", "23", "1998년 5월 31일생입니다."),
        ProfileData("파트", "안드로이드", "안드로이드 파트 YB입니다."),
        ProfileData("Github", "www.github.com/kmebin", "repository : SOPT-27th-Android"),
        ProfileData("Blog", "blowhui.tistory.com", "J가 되고 싶은 P의 개발 블로그"),
        ProfileData("Sopt", "www.sopt.org", "SHOUT OUR PASSION TOGETHER! 27기 ON SOPT")
    )

    profileAdapter.notifyDataSetChanged()
}
```

</br>

* **Option Menu**를 사용해서 RecyclerView의 Layout을 변경할 수 있게 했습니다.

```kotlin
override fun onCreateOptionsMenu(menu: Menu?): Boolean {

    val inflater = menuInflater
    inflater.inflate(R.menu.menu_layout, menu)

    return true
}

override fun onOptionsItemSelected(item: MenuItem): Boolean {
    
    when(item.itemId) {
        R.id.linear -> {
            rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            return true
        }
        R.id.grid -> {
            rv.layoutManager = GridLayoutManager(applicationContext, 2, LinearLayoutManager.VERTICAL, false)
            return true
        }
        else -> {
            return super.onOptionsItemSelected(item)
        }
    }
}
```

</br>

:bulb: **ItemTouchHelper**

> ItemTouchHelper.kt

* **ItemTouchHelper** 클래스를 사용하여 아이템을 drag&drop하고 swipe하는 것을 구현할 수 있습니다.

* `SimpleCallback()` 에 drag와 swipe할 방향을 각각 설정해 줍니다.

```kotlin
val helper = ItemTouchHelper(object :
    ItemTouchHelper.SimpleCallback(
        ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
        ItemTouchHelper.START){
```

</br>

* drag할 아이템의 위치와 drop할 아이템의 위치를 `notifyItemMoved()` 메소드에 전달하여 이동시킬 수 있습니다.

```kotlin
override fun onMove(
    recyclerView: RecyclerView,
    from: RecyclerView.ViewHolder,
    to: RecyclerView.ViewHolder
): Boolean {
    val fromPosition = from.adapterPosition
    val toPosition = to.adapterPosition
    adapter.notifyItemMoved(fromPosition, toPosition)
    return false
}
```

</br>

* swipe를 통해 제거할 아이템의 위치를 `notifyItemRemoved()` 에 전달하여 삭제할 수 있습니다.

```kotlin
override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
    adapter.data.removeAt(viewHolder.adapterPosition)
    adapter.notifyItemRemoved(viewHolder.adapterPosition)
}
```

</br></br>

## :memo: 3주차 과제 (2020-11-06)

> :computer: **필수 과제 (2020-11-06)**

<img width="300" alt="move" src="https://user-images.githubusercontent.com/72112845/98333766-cd59a000-2044-11eb-834d-7660b05392e9.gif"> 

* **전체 화면** : ViewPager + BottomNavigation

* **ViewPager** : 프로필 화면(HomeFragment) - 리사이클러뷰 화면(ProfileFragment) - 비어있는 화면(SettingsFragment)

* **프로필 화면(HomeFragment)** : TabLayout + ChildFragment(HomeInfoFragment - HomeOtherFragment)

</br>

## :memo: 코드 설명

:bulb: **ViewPager + BottomNavigation**

> MainPagerAdapter.kt

* **ViewPager**도 RecylerView와 같이 입력받은 데이터 리스트를 화면에 배치하기 위한 **Adapter** 구현이 필요합니다.

* `getItem()`에 보여줄 3개의 Fragment를 지정합니다.

```kotlin
class MainPagerAdapter(fm: FragmentManager)
    : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    var fragments = listOf<Fragment>()

    override fun getItem(position: Int): Fragment = when(position){
        0 -> HomeFragment()
        1 -> ProfileFragment()
        2 -> SettingsFragment()
        else -> throw IllegalStateException("Unexpected position $position")
    }

    override fun getCount(): Int = 3
}
```

</br>

> MainActivity.kt

* `onCreate()`에서 ViewPager에 선언한 Adapter를 장착합니다.

* `onStart()`에서 2가지 리스너들을 설정해 주었습니다.

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vp_main.adapter = MainPagerAdapter(supportFragmentManager)
    }

    override fun onStart() {
        super.onStart()

        vp_main.addViewPagerListener(bottom_navi)
        bottom_navi.setBottomNavigationListener(vp_main)
    }
}
```

</br>

> addViewPagerListener.kt

* ViewPager의 **화면 전환**을 감지하는 리스너입니다.

```kotlin
fun ViewPager.addViewPagerListener(bottomNavigationView: BottomNavigationView) {
    this.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{

        override fun onPageScrollStateChanged(state: Int) {}

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {}

        override fun onPageSelected(position: Int) {
            bottomNavigationView.menu.getItem(position).isChecked = true
        }
    })
}
```

</br>

> setBottomNavigationListener.kt

* BottomNavigation을 **세팅**하는 리스너입니다.

* 각 메뉴를 선택했을 때 ViewPager의 해당 Fragment로 화면이 전환됩니다.

```kotlin
fun BottomNavigationView.setBottomNavigationListener(viewPager: ViewPager) {
    this.setOnNavigationItemSelectedListener {
        when(it.itemId) {
            R.id.menu_profile -> viewPager.currentItem = 0
            R.id.menu_recycler -> viewPager.currentItem = 1
            R.id.menu_settings -> viewPager.currentItem = 2
        }
        true
    }
}
```

</br>

:bulb: **HomeFragment + TabLayout**

> HomeFragment.kt

* Fragment는 `onCreateView()`가 Activity에서의 onCreate() 역할을 합니다.

```kotlin
class HomeFragment : Fragment() {

    private lateinit var homeViewPagerAdapter: HomeViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }
    
    ...

}    
```

</br>

* `onViewCreated()`에서는 onCreateView()에서 return해 준 View들을 가지고 다룰 수 있습니다.

* `setupWithViewPager()`로 **TabLayout**과 ViewPager를 연동합니다.

* 탭 아이템의 title을 설정하는 `getTabAt()`는 반드시 연동 후에 작성해야 합니다.

```kotlin
class HomeFragment : Fragment() {
    
    ...

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewPagerAdapter = HomeViewPagerAdapter(childFragmentManager)
        vp_home.adapter = homeViewPagerAdapter

        tl_home.setupWithViewPager(vp_home)
        tl_home.apply {
            getTabAt(0)?.text = "INFO"
            getTabAt(1)?.text = "OTHER"
        }
        super.onViewCreated(view, savedInstanceState)
    }
}
```
