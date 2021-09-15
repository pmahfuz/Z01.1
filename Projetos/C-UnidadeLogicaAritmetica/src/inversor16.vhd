-- Elementos de Sistemas
-- by Luciano Soares
-- inversor16.vhd

-- Arquivo : Inversor16.vhd
-- Descrição : Inverte um vetor de entrada quando o bit de controle n 
-- (nx ou ny) for igual a '1', e não modifica o vetor de entrada caso contrário. 
-- O resultado é um novo vetor de 16 bits.
-- Dependência: Não tem.a

library IEEE;
use IEEE.STD_LOGIC_1164.all;

entity inversor16 is
  port(
        z   : in STD_LOGIC;
        a   : in STD_LOGIC_VECTOR(15 downto 0);
        y   : out STD_LOGIC_VECTOR(15 downto 0)
      );
end entity;

architecture rtl of inversor16 is
  -- Aqui declaramos sinais (fios auxiliares)
  -- e componentes (outros módulos) que serao
  -- utilizados nesse modulo.

begin
  y <= not a when (z = '1') else
    a;
    
end architecture;
