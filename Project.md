# Requisitos para correr el proyecto

1. **Android Studio**: Para poder correr el proyecto se necesita tener instalado Android Studio, se puede descargar desde [aqui](https://developer.android.com/studio).

2. **Emulador o Dispositivo Fisico**: Para poder correr la aplicación se necesita un emulador o un dispositivo fisico, se puede seguir la guia de como configurar un emulador desde [aqui](https://developer.android.com/studio/run/emulator).

3. **Clonar el repositorio**: Para poder correr el proyecto se necesita clonar el repositorio, se puede clonar desde [aqui](https://github.com/KaimDev/sales-management-android).

4. **Configurar el proyecto**: Una vez clonado el repositorio, se debe abrir el proyecto en Android Studio y esperar a que se sincronice, si hay algun problema con la sincronización se puede hacer de forma manual desde el menu de Android Studio **File -> Sync Project with Gradle Files**.

## Explicación del codigo

## Archivos importantes

Todo lo importante esta dentro de:

```bash

#Recursos graficos:
app/src/main/res/

#Logica de la aplicación y todas las cosas relacionadas con codigo:
app/src/main/java/com/example/salesmanagement/

```

**Para que una vista en android sea funcional tiene 2 partes base:**

1. El layout (un archivo xml)
2. La logica (un archivo kt)

**TODOS LOS ARCHIVOS XML TIENEN QUE IR DENTRO DE SU RESPECTIVA CARPETA, REGLA DE ANDROID.**

**Los archivos kotlin pueden ir guardados en cualquier carpeta dentro de _salesmanagement/_**

Una vista en android se le conoce como **Actividad** (Activity).

El proyecto utiliza la arquitectura de fragmentos, un fragmento es una vista reutilizable de android, para que esta pueda funcionar necesita de un Activity que lo cargue.

El proyecto cuenta con un unico Activity (MainActivity.kt) que se encarga de cagar los fragmentos que necesite en el momento correcto.

## Activities

Archivo principal:

### MainActivity.kt

Para hacer un activity se debe heredad la clase **AppCompatActivity**

```kt
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity()
```

Seguido se debe sobreescribir el metodo donde se va especificar el archivo xml que corresponde a la actividad:

```kt
override fun onCreate(savedInstanceState: Bundle?)
```

Kotlin cuenta con una caracteristica llamada **Binding** que te genera automaticamente una clase para enlazar los componentes del layout, esto une un archivo Kotlin y un XML siempre y cuando se llamen igual segun la convención de android.

Para que este archivo se pueda generar se debe respetar la convención de android:

Para archivos kotlin se debe escribir en pascal case: **MainActivity.kt**
Para archivos xml se debe escribir con snake case: **main_activity.xml**

Esto generará automaticamente un archivo que va enlazar estos dos llamado: **ActivityMainBinding**

Para finalizar el enlace se debe configurar en la funcion **onCreate**

```kt
class MainActivity : AppCompatActivity()
{

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
```

Ahora para manejar la navegacion con fragmentos se debe configurar mas cosas:

```kt

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Se asigna un Action bar personalizado.
        setSupportActionBar(binding.appBarMain.toolbar)  

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView

        // Asignamos el archivo xml donde esta el componente "Fragment" 
        // el cual se va reemplazar por el fragmento necesario.
        val navController = findNavController(R.id.nav_host_fragment_content_main) 

        // Aqui especificamos las vistas por su id que se asignaron
        // en el navigation/navigation_mobile.xml
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_customer, R.id.nav_product
            ), drawerLayout 
        )
        
        // Finalmente se pasa las anteriores configuraciones a esta 
        // funcion para que termine de hacer la logica de la navegacion.
        setupActionBarWithNavController(navController, appBarConfiguration) 
        
        // Aqui le decimos que el Menu lateral de la app
        // va poder interactuar con la navegacion.
        navView.setupWithNavController(navController) 
    }

    // Esta funcion sirve para especificar un menu en la parte superior de la pantalla
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // Aqui especificamos que pasará cuando se presione la tecla regresar en el telefono.
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

```

Todas las rutas de navegacion estan definidas dentro de:

```bash
app/src/main/res/navigation/mobile_navigation.xml
```

## Fragments

Los Fragments usa el patron de arquitectura MVVM (Modelo vista, vista modelo), basicamente es una arquitectura que separa la logica del Activity o Fragment en otro archivo, dejando unicamente configuración de la vista en su archivo Activity o Fragment y logica como acceso a base de datos, calculos, etc, en otro archivo.

Para hacer un fragmento se debe heredar la clase **Fragment**

### Customer

_Ruta_:

```bash
app/src/main/java/com/example/salesmanagement/ui/customer
```

La vista de Customer cuenta con 3 archivos kotlin y 1 layout xml

1. CustomerFragment.kt
2. CustomerAdapter.kt
3. CustomerViewModel.kt
4. fragment_customer.xml

_Que hace la vista Customer?_

La vista **Customer** se encarga de mostrar la lista de clientes existentes.

_Como lo hace?_

El **CustomerFragment** carga el layout especifico de la vista, hace las configuraciones iniciales, luego en el **CustmoerViewModel** (el encargado de la logica) hace la petición de todos los clientes a la base de datos, una vez que lo recibe lo envia al Fragment, y el fragment lo envía al **CustomerAdapter** el encargado de mostrar la lista de clientes de forma dinamica, o sea, si la lista se actualiza, el Adapter va actualizar la vista.

### CustomerFragment.kt

El archivo hereda la clase **Fragment**, al igual que el **AppCompatActivity** tiene un metodo que se tiene que sobre escribir para poder cargar el layout llamada **onCreateView()** y sigue el mismo comportamiento de **Binding**, se crea automaticamente ese archivo para poder enlazar los componentes del layout con el archivo kotlin

```kt
class CustomerFragment : Fragment() {}
```

El **CustomerFragment** tiene un componente en su layout llamado **RecyclerView**, este componente se encarga de mostrar una lista de lo que sea de forma dinamica, asi estará seguro que siempre que la lista de clientes se actualice, se mostrará en la vista.

### _RecyclerView_

Un recyclerview es un componente un poco complejo de entender, pero consta de 3 partes:

1. El diseño de como se mostrara la lista (un layout / xml)
2. Un View Holder que es como un activity o un fragment, se encarga de la configuracion inicial de la vista
3. Un Adapter, el mas importante. Este se encarga de recibir la lista y de mostrarla en la aplicación.

### _Vincular el view model_

Para que toda la logica de la vista se pueda hacer, se necesita un **ViewModel**, este se encarga de hacer las peticiones a la base de datos, calculos, etc.

Para vincular el **ViewModel** con el **Fragment** se debe hacer lo siguiente:

```kt
private val customerViewModel: CustomerViewModel by viewModels()
```

O tambien se hace

```kt
private lateinit var customerViewModel: CustomerViewModel

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    customerViewModel = ViewModelProvider(this).get(CustomerViewModel::class.java)
}
```

El archivo **CustomerFragment.kt** tiene 2 metodos privados que son de configuración unicamente (esto es para seguir el patron de arquitectura MVVM)

```kt

// Este metodo es para agregar un evento click al boton de agregar un Customer, 
// al tocar el boton te enviará a otra vista.
private fun initListeners()
{
    binding.fabAddCustomer.setOnClickListener { goToAddCustomer() }
}

```

```kt

private fun initRecyclerView()
{
    // Esta variable guarda la referencia al componente en el layout (El RecyclerView)
    val recyclerView = binding.rvCustomer
    
    // Se crea un objeto del tipo Adapter.
    val adapter = CustomerAdapter(binding.tvEmpty)
    
    // Luego al RecyclerView se le asigna el adapter
    recyclerView.adapter = adapter
    
    // Se le indica como va organizar los elementos, y se le asgina un LinearLayoutManager para que todos vayan de forma vertical
    recyclerView.layoutManager =
        androidx.recyclerview.widget.LinearLayoutManager(requireContext())
    
    // Hacemos una llamada al ViewModel para que traiga la lista de clientes 
    // de la base de datos y usamos el motodo `observe()` para que nos 
    // notifique de cualquier cambio en la lista. 
    // Luego la lista se la enviamos al Adapter con el metodo `setData()`
    customerViewModel.customers.observe(viewLifecycleOwner) { customer ->
        adapter.setData(customer)
    }
}

```

### CustomerAdapter.kt

Este archivo es el encargado de mostrar la lista de clientes en la vista, se encarga de mostrar la lista de forma dinamica, si la lista se actualiza, el adapter se encarga de actualizar la vista.

```kt
class CustomerAdapter(private val tvEmpty: TextView) : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder>()
```

El **CustomerAdapter** tiene 2 metodos que son de configuración unicamente (esto es para seguir el patron de arquitectura MVVM)

```kt

// Este metodo se encarga de recibir la lista desde el ViewModel y notificar que fue recibida.

fun setData(customers: List<Customer>)
{
    this.customers = customers
    notifyDataSetChanged()
    tvEmpty.visibility = if (customers.isEmpty()) View.VISIBLE else View.GONE
}

```

```kt

// Este metodo se encarga de crear la vista de un cliente en la lista

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder
{
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_customer, parent, false)
    return CustomerViewHolder(view)
}

```

```kt

// Este metodo se encarga de mostrar la lista de clientes en la vista

override fun onBindViewHolder(holder: CustomerHolder, position: Int)
{
    val currentItem = customerList[position]
    holder.itemView.findViewById<TextView>(R.id.tvName).text = currentItem.name
    holder.itemView.findViewById<TextView>(R.id.tvPhone).text = currentItem.phone
    holder.itemView.findViewById<TextView>(R.id.tvEmail).text = currentItem.email
    holder.itemView.findViewById<TextView>(R.id.tvAddress).text = currentItem.address

    // Navigate to update customer fragment
    holder.itemView.findViewById<RelativeLayout>(R.id.customer_holder_view).setOnClickListener {
        val action = CustomerFragmentDirections.actionNavCustomerToNavUpdateCustome(currentItem)
        holder.itemView.findNavController().navigate(action)
    }
}

```

### CustomerViewModel.kt

Este archivo es el encargado de hacer las peticiones a la base de datos.

```kt

class CustomerViewModel(application: Application): AndroidViewModel(application)
{
    // Esta variable es la que se encarga de traer la lista de clientes de la base de datos
    val customers: LiveData<List<Customer>>

    // Esta variable es la que se encarga de hacer las peticiones a la base de datos
    private val customerRepository: CustomerRepository

    init
    {
        // Se crea una instancia de la base de datos
        val customerDao = AppDatabase.getDatabase(application).customerDao()

        // Se crea una instancia del repositorio
        customerRepository = CustomerRepository(customerDao)
        
        // Se le asigna a la variable `customers` la lista de clientes que trae el repositorio
        customers = customerRepository.customers
    }
}

```

## Base de datos (Room Database)

Ruta

```bash
app/src/main/java/com/example/salesmanagement/database/
```

Room database es una libreria de android que se encarga de hacer la logica de la base de datos, es un poco compleja de entender pero se puede resumir en 3 partes:

1. **Entity**: Es la clase que representa una tabla en la base de datos.

2. **Dao**: En el dao definimos las consultas que se pueden hacer a la tabla.

3. **AppDatabase**: En este archivo usamos un patron de diseño llamado Singleton, que se encarga de crear una unica instancia de la base de datos.

Tabmien hacemos uso de un patron de diseño llamado **Repository**, este se encarga de recibir las peticiones de los **ViewModels**, aqui se puede validar la petición y hacer la logica necesaria antes de enviar la petición a la base de datos.

### Entities

Ruta

```bash
app/src/main/java/com/example/salesmanagement/database/entities/
```

En esta carpeta se encuentran las clases que representan las tablas de la base de datos.

### Daos

Ruta

```bash
app/src/main/java/com/example/salesmanagement/database/daos/
```

En esta carpeta se encuentran las interfaces que definen las consultas que se pueden hacer a la base de datos como un CRUD (Create, Read, Update, Delete).

### Repositories

Ruta

```bash
app/src/main/java/com/example/salesmanagement/database/repositories/
```

## Validations

Ruta

```bash
app/src/main/java/com/example/salesmanagement/validations/
```

En esta carpeta se encuentran las clases que se encargan de validar los datos que se ingresan en la aplicación.

Para validar un formulario de insertar o actualizar se debe validar la información que se esta dando.

Los inputs o campos de texto tienen un evento llamado **addTextChangedListener()**, este recibe una funcion de tipo **TextWatcher** que tiene 3 metodos:

1. **beforeTextChanged()**: Se ejecuta antes de que el texto cambie.
2. **onTextChanged()**: Se ejecuta cuando el texto cambia.
3. **afterTextChanged()**: Se ejecuta despues de que el texto cambie.

Se han creado validación customizadas y reutilizables para validar los campos de texto en cualquier vista de la aplicación.

En el archivo **InsertCustomerFragment.kt**:

```bash
app/src/main/java/com/example/salesmanagement/ui/customer/insert/
```

Se configura los inputs con sus respectivas validaciones.

```kt

private fun setUpListeners()
    {
        // Se obtienen los inputs de la vista
        val tilCustomerName = _binding!!.tilCustomerName
        val tilCustomerPhone = _binding!!.tilCustomerPhone
        val tilCustomerEmail = _binding!!.tilCustomerEmail
        val tilCustomerAddress = _binding!!.tilCustomerAddress

        // Se le asigna a cada input su respectiva validación
        tilCustomerName.editText!!.addTextChangedListener(TextIsNotEmptyValidator(tilCustomerName))

        // Se le asigna a cada input su respectiva validación
        tilCustomerPhone.editText!!.addTextChangedListener(PhoneValidator(tilCustomerPhone))

        // Se le asigna a cada input su respectiva validación
        tilCustomerEmail.editText!!.addTextChangedListener(EmailValidator(tilCustomerEmail))

        // Y al boton de guardar se le asigna un evento clic para que envie la información al ViewModel para ser procesada y guardada en la base de datos.
        _binding!!.fabSave.setOnClickListener {
            val result = viewModel.saveCustomer(
                tilCustomerName,
                tilCustomerPhone,
                tilCustomerEmail,
                tilCustomerAddress
            )

            if (result)
            {
                // Finalzada la operación se envia al usuario a la vista de Customer
                findNavController().navigate(R.id.action_nav_insert_customer_to_nav_customer)
            }
        }
    }
```

De vuelta en la carpeta de Validations se encuentran varios archivos de validaciónes, el principal es **ValidationBase.kt** que es la clase base de todas las validaciones.

Cuando se quiera hacer una validación se debe heredar de esta clase y sobreescribir el metodo **validate()**. Ya que esta clase cuenta con metodos virtuales listos para ser usados.

Y usando polimorfismo se puede pasar una validación a los inputs de texto.

```kt

// Se crea el validador y se hereda la clase ValidatorBase
class EmailValidator(private val input: TextInputLayout) : ValidatorBase(input)
{
    // Se implementa el metodo validate() que se encarga de validar el campo de texto
    override fun validate(): Boolean
    {
        // Se obtiene el texto del input
        val text = input.editText!!.text.toString().trim()
        
        // Se crea una expresión regular para validar el email
        val regex = Regex("^[A-Za-z0-9+_.-]+@(.+)\$")

        // Se retorna un booleano si el texto esta vacio o si la expresión regular coincide con el texto
        return text.isEmpty() || regex.matches(text)
    }

    // Se implementa el metodo getErrorMessage() que se encarga de devolver un mensaje de error si la validación falla
    override fun getErrorMessage(): String
    {
        return "Invalid Email!"
    }
}

```

## RES

Ruta

```bash
app/src/main/res/
```

En esta carpeta se encuentran todos los recursos graficos de la aplicación, como imagenes, colores, estilos, textos, layouts etc.

Esta carpeta es muy importante ya que es la que se encarga de darle la apariencia a la aplicación.

Y es facil que algo salga mal si no se ordenan los archivos de forma correcta.

## Layouts

En esta carpeta se encuentran todos los archivos xml que representan las vistas de la aplicación.

## Navigation

En esta carpeta se encuentra el archivo **mobile_navigation.xml** que se encarga de definir las rutas de navegación de la aplicación.

## Values

En esta carpeta se encuentran los archivos xml que contienen los valores de la aplicación, como colores, estilos, textos, dimensiones, etc.

## Menu

En esta carpeta se encuentran los archivos xml que contienen los menus de la aplicación.

## Drawables

En esta carpeta se encuentran las imagenes de la aplicación.

## Mipmap

En esta carpeta se encuentran los iconos de la aplicación.

## Anim

En esta carpeta se encuentran las animaciones de la aplicación.

## Raw

En esta carpeta se encuentran los archivos de audio, video, etc.
