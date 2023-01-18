# Activité pratique 3 : Architecture Micro-services avec Spring Cloud

## Travail à faire :

1. Créer le micro service Customer-service
      - Créer Tentité Customer
      - Créer l'interface CustomerRepository basée sur Spring Data 
      - Déployer l'API Restful du micro-service en utilisant Spring Data Rest
      - Tester le Micro service
2. Créer le micro service Inventory-service
      - Créer l'entité Product
      - Créer l'interface ProductRepository basée sur Spring Data
      - Déployer (API Restful du micro-service en utilisant Spring Data Rest
      - Tester le Micro service
3. Créer la Gateway service en utilisant Spring Cloud Gateway
      - Tester la Service proxy en utilisant une configuration Statique basée sur le fichier application.yml
      - Tester la Service proxy en utilisant une configuration Statique basée une configuration Java
4. Créer l'annuaire Registry Service basé sur NetFlix Eureka Server
5. Tester le proxy en utilisant une configuration dynamique de Gestion des routes vers les micro services enregistrés dans l'annuaire Eureka Server
6. Créer Le service Billing-Service en utilisant Open Feign pour communiquer avec les services Customer-service et Inventory-service 7. Créer un client Angular qui permet d'afficher une facture
7. Créer un client Angular qui permet d'afficher une facture

### **Architecture micro-services**

![image](https://user-images.githubusercontent.com/4341904/204916527-d005d75c-3ca0-4a74-abb3-c618ea9d5bac.png)

## Pour customer-service

![image](https://user-images.githubusercontent.com/4341904/204916558-2c795ce7-a652-4670-928c-0ddb57d6bba9.png)

### Actuator

![image](https://user-images.githubusercontent.com/4341904/204916572-bed1d4ce-73b9-4bed-a84f-c34fc4e7f7bf.png) 

### H2 console

![image](https://user-images.githubusercontent.com/4341904/204916600-9b472e2d-7a28-49e3-8c60-e7e73d95aa8f.png)

## Pour Inventory-service

![image](https://user-images.githubusercontent.com/4341904/204916619-e160f627-abed-4d1c-9eb1-de3e1ef006b4.png)

### H2-console

![image](https://user-images.githubusercontent.com/4341904/204916667-9a967315-16d7-4dd0-b8ea-99acc4b21830.png)

## Pour le service GateWay

Créer un fichier application.yml dans ressources

![image](https://user-images.githubusercontent.com/4341904/204916714-6f05e4ed-56b0-4d22-942b-769d009eae33.png)

![image](https://user-images.githubusercontent.com/4341904/204916730-6129ae87-5456-4c32-8e5c-8dd3737159dd.png)
 
Et dans application.properties

![image](https://user-images.githubusercontent.com/4341904/204916787-00c78980-9f26-4720-b4ae-d45c528ebe37.png)

On essaie de communiquer directement avec la gateway et voir si on peut atteindre les micro-services ;
-	Customers

![image](https://user-images.githubusercontent.com/4341904/204916835-c51a099a-e8c5-4eae-9051-98c61cb13596.png)

-	Products

![image](https://user-images.githubusercontent.com/4341904/204916852-fa8bad8d-efb3-4214-b3da-a918ca55d557.png)

Donc, à travers la gateway on a pu communiquer avec 2 micro-services

Maintenant, on va désactiver la configuration par fichier application.yml par changer son nom et on va essayer la configuration JAVA.

![image](https://user-images.githubusercontent.com/4341904/204916871-eacd1a6c-9427-4681-aecd-befe34f4504b.png)

Et tout ça va marcher comme pour application.yml
Ces deux méthodes sont des méthodes statiques qu’on utilise quand l’adresse est fixe et on connait.

Pour faire dynamiquement, on va créer un nouveau projet eureka-discovery, avec juste la dépendance Eureka Server, et on met l’annotation.

![image](https://user-images.githubusercontent.com/4341904/204916920-25c4f4fc-8c6e-49ff-9d42-a8bbcaa5dcd7.png)

![image](https://user-images.githubusercontent.com/4341904/204916944-be9dca98-984b-4d98-b437-8b1551556cf6.png)

Dans Inventory-service et Customer-service et Gateway-service on change false à true

![image](https://user-images.githubusercontent.com/4341904/204916971-b94f78af-2b74-4758-99c6-328140b9808e.png)

## Dans Gateway-service

![image](https://user-images.githubusercontent.com/4341904/204916994-2dc6fde2-c0b9-40bc-8440-9681d0a4b77b.png)
 
lb c’est pour Load Balancer
Et maintenant, tout ça fonctionne bien, et juste avec le nom du micro-service

![image](https://user-images.githubusercontent.com/4341904/204917033-fbb5bde2-70de-4d32-b738-205c57a7275c.png)

![image](https://user-images.githubusercontent.com/4341904/204917061-69a1f708-a89e-41d6-baa5-9316f75741c2.png)

La méthode précédente, ce n’est pas évidente quand on a beaucoup de micro-service, pour cela on va essayer d’exploiter une méthode plus dynamique.

![image](https://user-images.githubusercontent.com/4341904/204917080-91650839-3d53-442b-a50c-72e38c1968df.png)

Maintenant, on peut atteindre notre micro-service par l’url mais on doit ajouter le nom du micro-service avant le Path

![image](https://user-images.githubusercontent.com/4341904/204917104-bc7c386b-f3e7-4b92-a443-d85738245046.png)

S’il y a plusieurs instances de même micro-service le Gateway utilise le principe de l’équilibrage de charge ; le spring cloud gateway utilise un service spring cloud qui s’appelle Ribon, qui est un service d’équilibrage de charge, on n’a pas l’exploiter explicitement mais il est utilisé implicitement, mais on peut le configurer nous-même.

## Pour Billing-service

![image](https://user-images.githubusercontent.com/4341904/204917151-92bad852-6311-454b-bbb0-0c35b79ea178.png)
 
On voit que l’ID est null, pour l’exposer on doit ajouter dans Customer-service avec RestConfiguration

![image](https://user-images.githubusercontent.com/4341904/204917173-48557cf4-2c00-4a18-8972-27dbc672a104.png)

Le même pour inventory

Lier les produits à une facture

![image](https://user-images.githubusercontent.com/4341904/204917200-e9ec4417-ee14-4cef-a626-e3d1fbccd1ed.png)

Problème de Boucle infinie

![image](https://user-images.githubusercontent.com/4341904/204917221-e50c7663-b4a4-431a-ad54-3bda4dbf432e.png)

Pour régler le problème

![image](https://user-images.githubusercontent.com/4341904/204917249-2277ca95-7071-4c7a-a0ed-582a61ce2690.png)

Le problème est résolu

![image](https://user-images.githubusercontent.com/4341904/204917278-bed70eb9-05af-4a28-865d-86d5a2b553f2.png)

Mais sans le nom du produit et les informations sur le client sauf son ID, pour cela on va essayer de les récupérer.

### Dans BillingRestController 

![image](https://user-images.githubusercontent.com/4341904/204917309-dc9ce7ad-4b35-4858-9c68-5b4bf1333987.png)

Et on va avoir le résultat suivant :

![image](https://user-images.githubusercontent.com/4341904/204917331-6f9e79a5-8da8-4763-8bd3-30ce639310e0.png)

Mais on aucun besoin d’ajouter les autres informations du produit puisqu’elles sont déjà présentes 
Donc on va ajouter seulement un attribut productName au productItem avec l’annotations @Transient puisqu’on ne veut pas créer cet attribut sur la table

![image](https://user-images.githubusercontent.com/4341904/204917365-f2c0b5d3-8c62-4d6b-bf1f-4ff36e38f695.png)

Et on modifie BillingRestController pour qu’il soit :

![image](https://user-images.githubusercontent.com/4341904/204917385-5edcf561-5cc8-44e2-a7ef-68beb2c725ff.png)

Le résultat :

![image](https://user-images.githubusercontent.com/4341904/204917411-325d4028-6f86-4f02-94e6-8fe4c33297eb.png)

# Partie 3 : client Angular

### Produits
![image](https://user-images.githubusercontent.com/4341904/213055465-13d6e947-c42b-4eb3-9110-c973aff2c4bf.png)

### Clients

![image](https://user-images.githubusercontent.com/4341904/213055611-59b74713-9e1a-451c-b08f-5ad4c73b2882.png)


### Ordres de client
![image](https://user-images.githubusercontent.com/4341904/213055802-e8b5aec8-6904-4cff-bc0b-f7248e8253dd.png)

### Facture

![image](https://user-images.githubusercontent.com/4341904/213055986-8694ed66-e33c-432c-b47b-59789182b09e.png)

### Realtime ordering - bills analysis
![analytics](https://user-images.githubusercontent.com/4341904/213058157-ebfa05da-40d4-4601-ae8a-210036758ce9.gif)





__________________________________________________________________________________________________________________

# Keycloak

![image](https://user-images.githubusercontent.com/4341904/211162619-4f492f6e-eb58-4a44-9c98-5516ea869a7c.png)

## refresh token

![image](https://user-images.githubusercontent.com/4341904/211163152-8675ebaf-b4d3-42ad-b220-4b78defacc52.png)
