#language: fr
  
  Fonctionnalité: Ajouter un bouteille au stock
    
    Scénario: Récupérer la liste vide des bouteilles
      Quand on récupère la liste des bouteilles
      Alors la liste renvoie le code 200
      Et la liste est vide
      
    Scénario: Ajouter une bouteille
      Etant donné la bouteille de "Chateau Margon" de provenance de "France" (id=1)
      Alors l'ajout d' une bouteille renvoie le code 201

    Scénario: Récupérer la liste vide des bouteilles
      Quand on récupère la liste des bouteilles
      Alors la liste renvoie le code 200
      Et la liste est contient 1 élément

    Scénario: Récupérer une bouteille par son identifiant
      Quand on récupère la bouteille avec l'id 1
      Alors la liste renvoie le code 200
      Et correspond à la bouteille de "Chateau Margon" de provenance de "France" (id=1)
# getBottles
# getBottlesById