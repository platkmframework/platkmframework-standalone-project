#Proyecto inicial

Descargue este proyecto para iniciar una implementación


#Actualice el setting.xml de maven.

1 - En la sección de mirrors adicione la siguiente entrada:
```

	<mirror>
		<id>platkmframework</id>
	  	<mirrorOf>*,!central</mirrorOf>
	  	<name>platkmframework repository</name>
	  	<url>https://nexus.platkmframework.io/repository/platkmframework-public/</url>
	</mirror> 
``` 

2- adicione un active profile para platmframework

```
<activeProfiles>
	<activeProfile>platkmframework</activeProfile>
</activeProfiles>
```


3- adicione un profile para platkmframework
```
	<profile>
		<id>platkmframework</id> 
	  	<activation>
	    	<jdk>platkmframework</jdk>
	  	</activation> 
	  	<repositories>
	    	<repository>
	      		<id>platkmframework</id>
	      		<name>Repository platkframework</name>
	      		<url>https://nexus.platkmframework.io/repository/platkmframework-public</url>
	      		<layout>default</layout>
	    	</repository>
	  	</repositories>
	</profile>
``` 

4- websocket client react
```
export const WebSocketExample = () =>{

    const socketUrl = 'ws://<server>/<topic>';
     
    useEffect(() => {
        const ws = new WebSocket(socketUrl)

        ws.onmessage = (ev) => {
            console.log(ev.data)
        }

        ws.onclose = (ev) => {
            console.log(ev)
        }

        ws.onerror = (ev) => {
            console.log(ev)
        }
        

      }, [])

    return (
        <>
                  
        </>
    )
}
```
