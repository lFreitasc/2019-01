package br.edu.utfpr.servico;

import br.edu.utfpr.dto.PaisDTO;
/*
    model != dto =! entity
    mvn clean spring-boot:run (por padrão porta 8080)
*/
@RestController
public class ServicoPais{
    List<PaisDTO> paises;

    public ServicoPais(){
        paises = Stream.of(
            PaisDTO.builder().id(1).codigoTelefone(55).nome("Brasil").sigla("BR").build();
            PaisDTO.builder().id(2).codigoTelefone(33).nome("Estados Unidos da America").sigla("USA").build();
            PaisDTO.builder().id(3).codigoTelefone(44).nome("Reino Unido").sigla("RU").build();
        ).collect(Collectors.toList());
    }

    @GetMapping("/servico/pais")
    public ResponseEntity<List<PaisDTO>> listar(){ //encapsula o retorno para manipular a saida
        return ResponseEntity.ok(paises);
    }

    @PostMapping("/servico/pais") //http post:8080/servico/pais codigoTelfone = xx nome ="x" siga="xx"
    public ResponseEntity<PaisDTO> criar(@RequestBody PaisDTO pais){
        pais.setId(paises.size() + 1);
        paises.add(pais);

        return ResponseEntity.status(201).body(pais); //cod que informa ok
    }

    @DeleteMapping("/servico/pais/{id}") //http delete:8080/serivo/pais/1
    public ResponseEntity excluir(@PathVariable int id){
        paises.removeIf(pais -> pais.getId() == id);//estudar filtro (filter), retorna um Option, para tratar um possivel null

        return ResponseEntity.noContent().build(); //utiliza o padrão builder e necessita ser construido
    }

    @PutMapping("/servico/pais/{id}") // http put:8080/servico/pais/2 nome="Russia" sigla="RU" codigoTelefone=00
    public ResponseEntity<PaisDTO> alterar(@PathVariable int id, @RequestBody PaisDTO pais){
        //recuperar pais pelo id
        Optional<PaisDTO> paisExistente = paises.stream().filter(paisAtual -> paisAtual.getId() == id).findAny();

        //alterar dados no pais recuprado
        paisExistente.ifPresent(pExistente -> {
            pExistente.setName(pais.getNome());
            pExistente.setSigla(pais.getSigla());
            pExistente.setCodigoTelefone(pais.getCodigoTelefone);
        })
        //retornar o resultado
        return ResponseEntity.of(paisExistente);
    }

    @GetMapping("/servico/pais/{id}")
    public ResponseEntity<PaisDTO> listar(@PathVariable int id){
        Option<PaisDTO> pais = paises.stream().filter(p -> p.getId() == id).findAny()//retorna o primeiro que encontrar
        return ResponseEntity.of(pais)
    }

}
