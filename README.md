# tp_pm_20172
Trabalho prático final de Programação Modular 2017/2

O problema
Agendamento de Atendimento
A clínica Saracura deseja desenvolver um programa para controlar o atendimento a seus clientes.
A clínica oferece serviços de consultas em várias especialidades e a realização de exames de
imagens (Tomografias, Ultrassom, Raio X, etc.). Para atendimento a clinica possui um corpo de
funcionários administrativos, um corpo de médicos e um corpo de técnicos habilitados a realizar
alguns dos exames.

Os médicos são previamente cadastrados no sistema. As informações do cadastro são: nome do
médico, especialidade, e uma agenda para marcação de consultas que abrange no máximo os
vinte dias uteis do mês corrente. Supor que uma consulta necessite e 30 minutos e que o
atendimento ocorra de 8:00 até 17:00 somente. Os dados podem ser editados em um arquivo e
lidos para o sistema. Opcionalmente pode ser projetada uma interface gráfica para esse
cadastramento.

Para usar os serviços da clinica, o cliente deve agendar o atendimento com antecedência. No
momento do agendamento, o sistema verifica se o cliente já está cadastrado. Se não estiver, o
sistema deve abrir uma janela para cadastrar o nome do cliente, número da identidade e do CPF,
endereço, telefone e data de nascimento. Na sequencia o cliente escolhe o atendimento: consulta
ou exame.

No caso da consulta o cliente escolhe a especialidade e o sistema lista os médicos cadastrados
nesta especialidade. O cliente então seleciona um dos médicos cadastrados. O sistema apresenta
as datas disponíveis, o usuário seleciona a data que lhe interessa e o sistema apresenta os
horários ainda disponíveis para marcação. Em cada caso além das opções de escolha, o cliente
poderá optar por cancelar o agendamento, ou retornar à tela anterior.

Para os exames também deve ser criado um cadastro com o nome dos exames e uma agenda para
30 dias do mês corrente. Supor que cada exame necessita de 30 minutos para ser realizado. Este
cadastro pode ser editado em arquivo e lido para o sistema, ou criado via interface gráfica.
Para o agendamento de exames, o cliente deve fornecer o nome do exame. O sistema verifica se
aquele exame é realizado pela clinica e fornece uma listagem das próximas datas disponíveis
para sua realização. O usuário pode escolher uma das datas ou desistir do agendamento. Se o
usuário escolher uma data o sistema fornece os horários disponíveis. O usuário escolhe um dos
horários disponíveis, ou desiste do agendamento. O usuário também pode optar por retornar à
tela anterior.

Quando o usuário completa o agendamento, no caso de consulta, o sistema deve registrar: a
especialidade, o nome do medico, a data, a hora, nome do paciente e tipo de atendimento: 
cortesia, particular ou por convênio. No caso de exames devem ser armazenados o nome do
exame, nome do cliente, data, hora e o tipo de atendimento: cortesia, particular ou convênio.

Para cada tipo de atendimento (consulta ou exame) o sistema deve realizar um procedimento de
autorização. No caso do atendimento de cortesia, a autorização deve ser dada pela diretoria. No
caso de atendimento particular o sistema deve verificar o pagamento é confiável: no caso de
pagamento em espécie a aprovação sempre ocorre. No caso de pagamento com cheque, uma
consulta ao Serasa deve ser feita, usando o CPF, antes da autorização. No caso de convênio, uma
consulta ao operador do convênio, usando o número de registro no convênio e o nome do cliente,
deverá ser realizada. Cartões de debito e crédito também estão sujeitos a consultas ao operador
do cartão. Caso um atendimento não seja autorizado, uma mensagem deverá ser emitida e o
agendamento cancelado.

A autorização normalmente é dada por uma entidade fora do sistema: pela diretoria – no caso de
atendimento de cortesia, pelo Serasa – no caso de cheques ou pela operadora de convênio – no
caso de atendimento por convênio. Para atender ao processo de autorização, deverá ser criada
uma classe contendo métodos para cada uma das cinco situações possíveis: 1) no caso de
atendimento de cortesia, a cada cinco pedidos, um deverá ser recusado; 2) no caso de cheques,
um sorteio aleatório deverá ser usado para recusar, ou não, o cheque; 3) no caso de cartão (débito
ou crédito) supor que sempre podem ser liberados; 4) no caso de convênio, a cada dez
solicitações atendidas, uma deverá ser submetida a um sorteio aleatório para definir se o
atendimento será autorizado, ou não; 5) no caso de pagamento em dinheiro a autorização não tem
restrições. O objetivo dessa classe é simular o que acontece na atividade real de autorizar
atendimento em clinicas.

Um atendimento agendado pode ser cancelado. Neste caso o nome do cliente será fornecido e o
sistema deverá identificar o agendamento, listar uma mensagem contendo os dados do
agendamento e retirá-lo do sistema. Caso se tente cancelar um agendamento inexistente, um
evento de exceção deverá informar tal situação e retornar o sistema para a tela inicial.

O Trabalho
O foco do trabalho é o desenvolvimento e a implementação do que foi descrito, ou seja, apenas a
etapa de agendamento e autorização de atendimento. Não há, por exemplo, descrição sobre como
controlar a realização do atendimento ou sobre o cadastro de funcionários administrativos e
técnicos. Mas os médicos deverão ser cadastrados e certamente o sistema posteriormente poderá
expandido e os cadastros dos funcionários e técnicos incluídos. Logo ao definir classes para
cadastramento de pessoas essa possibilidade deverá ser levada em consideração.
O projeto deve ser desenvolvido sob o paradigma de Orientação a Objetos e usando Interface
Gráfica. Os dados de teste deverão ser criados pelos projetistas.
