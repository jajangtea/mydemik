-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2017 at 06:37 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydemik`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenissurat`
--

CREATE TABLE `jenissurat` (
  `idJenis` int(11) NOT NULL,
  `kodeJenis` varchar(10) NOT NULL,
  `jenisSurat` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jenissurat`
--

INSERT INTO `jenissurat` (`idJenis`, `kodeJenis`, `jenisSurat`) VALUES
(1, 'AKT', 'Aktif Kuliah'),
(2, 'UND', 'Undangan'),
(3, 'PJM', 'Pinjam Barang');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `idKategori` int(11) NOT NULL,
  `namaKategori` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`idKategori`, `namaKategori`) VALUES
(1, 'Surat Masuk'),
(2, 'Surat Keluar');

-- --------------------------------------------------------

--
-- Table structure for table `keperluan`
--

CREATE TABLE `keperluan` (
  `idKeperluan` int(11) NOT NULL,
  `Keperluan` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `keperluan`
--

INSERT INTO `keperluan` (`idKeperluan`, `Keperluan`) VALUES
(1, 'Permohonan Beasiswa'),
(2, 'Mencari Kerja');

-- --------------------------------------------------------

--
-- Table structure for table `mahasiswa`
--

CREATE TABLE `mahasiswa` (
  `idMahasiswa` int(11) NOT NULL,
  `nim` int(11) NOT NULL,
  `nama` varchar(250) NOT NULL,
  `alamat` varchar(250) NOT NULL,
  `tlp` varchar(15) NOT NULL,
  `idProdi` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mahasiswa`
--

INSERT INTO `mahasiswa` (`idMahasiswa`, `nim`, `nama`, `alamat`, `tlp`, `idProdi`) VALUES
(1, 12090411, 'Jajang Nurjaman', 'Tanjungpinang', '081991364199', 1),
(2, 1209410, 'Herman Susilo', 'Kijang', '0819213211222', 2);

-- --------------------------------------------------------

--
-- Table structure for table `perusahaan`
--

CREATE TABLE `perusahaan` (
  `idPerusahaan` int(11) NOT NULL,
  `namaPerusahaan` varchar(200) NOT NULL,
  `alamat` text NOT NULL,
  `tlp` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `perusahaan`
--

INSERT INTO `perusahaan` (`idPerusahaan`, `namaPerusahaan`, `alamat`, `tlp`) VALUES
(1, 'abcd', 'kijang', '1234'),
(2, 'deef', 'Batam', '08321321');

-- --------------------------------------------------------

--
-- Table structure for table `prodi`
--

CREATE TABLE `prodi` (
  `idProdi` int(11) NOT NULL,
  `kodeProdi` varchar(5) NOT NULL,
  `namaProdi` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prodi`
--

INSERT INTO `prodi` (`idProdi`, `kodeProdi`, `namaProdi`) VALUES
(1, 'SI', 'Sistem Informasi'),
(2, 'KA', 'Komputer Akuntansi'),
(3, 'TI', 'Teknik Informatika');

-- --------------------------------------------------------

--
-- Table structure for table `surat`
--

CREATE TABLE `surat` (
  `idSurat` int(11) NOT NULL,
  `idJenis` int(11) NOT NULL,
  `tanggalSurat` date NOT NULL,
  `idMahasiswa` int(11) NOT NULL,
  `idKeperluan` int(11) NOT NULL,
  `idKategori` int(11) NOT NULL,
  `tanggalBuat` date NOT NULL,
  `noSurat` varchar(200) NOT NULL,
  `tujuan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `surat`
--

INSERT INTO `surat` (`idSurat`, `idJenis`, `tanggalSurat`, `idMahasiswa`, `idKeperluan`, `idKategori`, `tanggalBuat`, `noSurat`, `tujuan`) VALUES
(3, 1, '2017-04-10', 1, 1, 1, '2017-04-10', 'dsad', 2),
(4, 1, '2017-04-10', 2, 1, 1, '2017-04-10', 'dsa', 2),
(5, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(6, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(7, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(8, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(9, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(10, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(11, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(12, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(13, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(14, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(15, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(16, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(17, 1, '2017-04-10', 1, 1, 1, '2017-04-10', '', 2),
(18, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(19, 1, '2017-04-10', 1, 1, 1, '2017-04-10', 'jTextField1', 2),
(20, 1, '2017-04-10', 1, 1, 1, '2017-04-10', 'jTextField1', 2),
(21, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(23, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(24, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(26, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(27, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(28, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(29, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(30, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(31, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(32, 1, '2017-04-10', 2, 1, 1, '2017-04-10', '', 2),
(33, 1, '2017-04-10', 2, 1, 1, '2017-04-10', 'fdsfds', 1),
(34, 2, '2017-04-10', 2, 1, 1, '2017-04-10', 'gfsdgs', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `jenissurat`
--
ALTER TABLE `jenissurat`
  ADD PRIMARY KEY (`idJenis`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`idKategori`);

--
-- Indexes for table `keperluan`
--
ALTER TABLE `keperluan`
  ADD PRIMARY KEY (`idKeperluan`);

--
-- Indexes for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD PRIMARY KEY (`idMahasiswa`),
  ADD KEY `idProdi` (`idProdi`),
  ADD KEY `idProdi_2` (`idProdi`);

--
-- Indexes for table `perusahaan`
--
ALTER TABLE `perusahaan`
  ADD PRIMARY KEY (`idPerusahaan`);

--
-- Indexes for table `prodi`
--
ALTER TABLE `prodi`
  ADD PRIMARY KEY (`idProdi`);

--
-- Indexes for table `surat`
--
ALTER TABLE `surat`
  ADD PRIMARY KEY (`idSurat`),
  ADD KEY `idKeperluan` (`idKeperluan`),
  ADD KEY `idMahasiswa` (`idMahasiswa`),
  ADD KEY `idJenis` (`idJenis`),
  ADD KEY `idKategori` (`idKategori`),
  ADD KEY `tujuan` (`tujuan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `jenissurat`
--
ALTER TABLE `jenissurat`
  MODIFY `idJenis` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `idKategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `keperluan`
--
ALTER TABLE `keperluan`
  MODIFY `idKeperluan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  MODIFY `idMahasiswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `perusahaan`
--
ALTER TABLE `perusahaan`
  MODIFY `idPerusahaan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `prodi`
--
ALTER TABLE `prodi`
  MODIFY `idProdi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `surat`
--
ALTER TABLE `surat`
  MODIFY `idSurat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `mahasiswa`
--
ALTER TABLE `mahasiswa`
  ADD CONSTRAINT `mahasiswa_ibfk_1` FOREIGN KEY (`idProdi`) REFERENCES `prodi` (`idProdi`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `surat`
--
ALTER TABLE `surat`
  ADD CONSTRAINT `surat_ibfk_1` FOREIGN KEY (`idJenis`) REFERENCES `jenissurat` (`idJenis`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `surat_ibfk_2` FOREIGN KEY (`idKeperluan`) REFERENCES `keperluan` (`idKeperluan`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `surat_ibfk_3` FOREIGN KEY (`idKategori`) REFERENCES `kategori` (`idKategori`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `surat_ibfk_4` FOREIGN KEY (`idMahasiswa`) REFERENCES `mahasiswa` (`idMahasiswa`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `surat_ibfk_5` FOREIGN KEY (`tujuan`) REFERENCES `perusahaan` (`idPerusahaan`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
